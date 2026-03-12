plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.7"
    java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	// Needed for @RestController, @GetMapping, @RequestParam, etc.
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Google OAuth2
	implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.google.firebase:firebase-admin:9.2.0")

	// Testing helpers
	testImplementation("org.springframework.security:spring-security-test")
	// Needed for Mockito static mocking
	testImplementation("org.mockito:mockito-inline:5.2.0")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-XX:+EnableDynamicAgentLoading")
}
