package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.RETURNS_SELF;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.config.FirebaseConfig;
import com.example.demo.security.SecurityConfig;
import com.example.demo.controller.UserController;
import com.example.demo.security.FirebaseAuthenticationFilter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JUnit tests for:
 * - FirebaseConfig
 * - UserController
 * - FirebaseAuthenticationFilter
 * - SecurityConfig
 */
public class DemoApplicationTests {

    // --------------------------------------------------------------------------------------------
    // FirebaseConfig.java tests
    // --------------------------------------------------------------------------------------------
    @Nested
    class FirebaseConfigTests {

        @AfterEach
        void cleanup() {
            SecurityContextHolder.clearContext();
        }

        @Test
        void initialize_whenServiceAccountMissing_throwsRuntimeException() {
            // FirebaseConfig loads firebase-service-account.json
            // If it's missing, initialize() must throw RuntimeException
            InputStream is = FirebaseConfigTests.class.getClassLoader()
                    .getResourceAsStream("firebase-service-account.json");

            Assumptions.assumeTrue(is == null,
                    "firebase-service-account.json exists on classpath; skipping missing-resource test.");

            FirebaseConfig config = new FirebaseConfig();
            RuntimeException ex = assertThrows(RuntimeException.class, config::initialize);
            assertTrue(ex.getMessage().contains("Failed to initialize Firebase"));
        }

        @Test
        void initialize_whenNoApps_callsInitializeApp_once() throws Exception {
            // Only runs if the resource exists
            InputStream is = FirebaseConfigTests.class.getClassLoader()
                    .getResourceAsStream("firebase-service-account.json");

            Assumptions.assumeTrue(is != null,
                    "firebase-service-account.json not found on classpath; skipping init-behavior test.");

            GoogleCredentials creds = mock(GoogleCredentials.class);

            try (MockedStatic<GoogleCredentials> googleCredsStatic = Mockito.mockStatic(GoogleCredentials.class);
                 MockedStatic<FirebaseApp> firebaseAppStatic = Mockito.mockStatic(FirebaseApp.class)) {

                googleCredsStatic.when(() -> GoogleCredentials.fromStream(any(InputStream.class)))
                        .thenReturn(creds);

                firebaseAppStatic.when(FirebaseApp::getApps).thenReturn(Collections.emptyList());
                firebaseAppStatic.when(() -> FirebaseApp.initializeApp(any(FirebaseOptions.class)))
                        .thenReturn(mock(FirebaseApp.class));

                FirebaseConfig config = new FirebaseConfig();
                assertDoesNotThrow(config::initialize);

                firebaseAppStatic.verify(() -> FirebaseApp.initializeApp(any(FirebaseOptions.class)), times(1));
            }
        }

        @Test
        void initialize_whenAppsExist_doesNotCallInitializeApp() throws Exception {
            InputStream is = FirebaseConfigTests.class.getClassLoader()
                    .getResourceAsStream("firebase-service-account.json");

            Assumptions.assumeTrue(is != null,
                    "firebase-service-account.json not found on classpath; skipping init-behavior test.");

            GoogleCredentials creds = mock(GoogleCredentials.class);
            FirebaseApp existing = mock(FirebaseApp.class);

            try (MockedStatic<GoogleCredentials> googleCredsStatic = Mockito.mockStatic(GoogleCredentials.class);
                 MockedStatic<FirebaseApp> firebaseAppStatic = Mockito.mockStatic(FirebaseApp.class)) {

                googleCredsStatic.when(() -> GoogleCredentials.fromStream(any(InputStream.class)))
                        .thenReturn(creds);

                firebaseAppStatic.when(FirebaseApp::getApps).thenReturn(List.of(existing));

                FirebaseConfig config = new FirebaseConfig();
                assertDoesNotThrow(config::initialize);

                firebaseAppStatic.verify(() -> FirebaseApp.initializeApp(any(FirebaseOptions.class)), never());
            }
        }
    }

    // --------------------------------------------------------------------------------------------
    // UserController.java tests
    // --------------------------------------------------------------------------------------------
    @Nested
    class UserControllerTests {

        @Test
        void profile_returnsHelloPlusPrincipal() {
            UserController controller = new UserController();
            String out = controller.profile("user@example.com");
            assertEquals("Hello, user@example.com", out);
        }

        @Test
        void profile_handlesNullPrincipal() {
            UserController controller = new UserController();
            String out = controller.profile(null);
            assertEquals("Hello, null", out);
        }
    }

    // --------------------------------------------------------------------------------------------
    // FirebaseAuthenticationFilter.java tests
    // --------------------------------------------------------------------------------------------
    @Nested
    class FirebaseAuthenticationFilterTests {

        @BeforeEach
        void clear() {
            SecurityContextHolder.clearContext();
        }

        @AfterEach
        void cleanup() {
            SecurityContextHolder.clearContext();
        }

        @Test
        void doFilterInternal_withoutAuthorizationHeader_doesNotAuthenticate_andCallsChain() throws Exception {
            FirebaseAuthenticationFilter filter = new FirebaseAuthenticationFilter();

            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse res = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);

            when(req.getHeader("Authorization")).thenReturn(null);

            filter.doFilter(req, res, chain);

            verify(chain, times(1)).doFilter(req, res);
            assertNull(SecurityContextHolder.getContext().getAuthentication());
        }

        @Test
        void doFilterInternal_withInvalidBearerToken_clearsContext_andCallsChain() throws Exception {
            FirebaseAuthenticationFilter filter = new FirebaseAuthenticationFilter();

            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse res = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);

            when(req.getHeader("Authorization")).thenReturn("Bearer bad.token");

            FirebaseAuth firebaseAuthMock = mock(FirebaseAuth.class);
            when(firebaseAuthMock.verifyIdToken("bad.token")).thenThrow(new RuntimeException("invalid"));

            try (MockedStatic<FirebaseAuth> firebaseAuthStatic = Mockito.mockStatic(FirebaseAuth.class)) {
                firebaseAuthStatic.when(FirebaseAuth::getInstance).thenReturn(firebaseAuthMock);

                // puts into the context first to prove it gets cleared
                SecurityContextHolder.getContext().setAuthentication(mock(Authentication.class));

                filter.doFilter(req, res, chain);

                assertNull(SecurityContextHolder.getContext().getAuthentication());
                verify(chain, times(1)).doFilter(req, res);
                verify(firebaseAuthMock, times(1)).verifyIdToken("bad.token");
            }
        }

        @Test
        void doFilterInternal_withValidBearerToken_setsPrincipalToEmail() throws Exception {
            FirebaseAuthenticationFilter filter = new FirebaseAuthenticationFilter();

            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse res = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);

            when(req.getHeader("Authorization")).thenReturn("Bearer good.token");

            FirebaseToken decoded = mock(FirebaseToken.class);
            when(decoded.getUid()).thenReturn("uid-123");
            when(decoded.getEmail()).thenReturn("user@example.com");

            FirebaseAuth firebaseAuthMock = mock(FirebaseAuth.class);
            when(firebaseAuthMock.verifyIdToken("good.token")).thenReturn(decoded);

            try (MockedStatic<FirebaseAuth> firebaseAuthStatic = Mockito.mockStatic(FirebaseAuth.class)) {
                firebaseAuthStatic.when(FirebaseAuth::getInstance).thenReturn(firebaseAuthMock);

                filter.doFilter(req, res, chain);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                assertNotNull(auth);
                assertEquals("user@example.com", auth.getPrincipal());
                verify(chain, times(1)).doFilter(req, res);
            }
        }

        @Test
        void doFilterInternal_withValidBearerToken_andNullEmail_setsPrincipalToUid() throws Exception {
            FirebaseAuthenticationFilter filter = new FirebaseAuthenticationFilter();

            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse res = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);

            when(req.getHeader("Authorization")).thenReturn("Bearer good.token");

            FirebaseToken decoded = mock(FirebaseToken.class);
            when(decoded.getUid()).thenReturn("uid-999");
            when(decoded.getEmail()).thenReturn(null);

            FirebaseAuth firebaseAuthMock = mock(FirebaseAuth.class);
            when(firebaseAuthMock.verifyIdToken("good.token")).thenReturn(decoded);

            try (MockedStatic<FirebaseAuth> firebaseAuthStatic = Mockito.mockStatic(FirebaseAuth.class)) {
                firebaseAuthStatic.when(FirebaseAuth::getInstance).thenReturn(firebaseAuthMock);

                filter.doFilter(req, res, chain);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                assertNotNull(auth);
                assertEquals("uid-999", auth.getPrincipal());
                verify(chain, times(1)).doFilter(req, res);
            }
        }
    }

    // --------------------------------------------------------------------------------------------
    // SecurityConfig.java tests
    // --------------------------------------------------------------------------------------------
    @Nested
	class SecurityConfigTests {

		@Test
		void filterChain_buildsAndAddsFirebaseFilterBeforeUsernamePasswordAuthFilter() throws Exception {
			SecurityConfig config = new SecurityConfig();

			HttpSecurity http = mock(HttpSecurity.class, RETURNS_SELF);

			// build() returns DefaultSecurityFilterChain in your version
			DefaultSecurityFilterChain chain = mock(DefaultSecurityFilterChain.class);
			when(http.build()).thenReturn(chain);

			var result = config.securityFilterChain(http);

			assertSame(chain, result);

			verify(http, times(1))
					.addFilterBefore(any(FirebaseAuthenticationFilter.class), eq(UsernamePasswordAuthenticationFilter.class));
			verify(http, times(1)).build();
		}
}
}
