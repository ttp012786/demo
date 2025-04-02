import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("kapt") version "1.9.0"

    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"

    alias(libs.plugins.openapi.generator)
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

group = "com.example"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

openApiGenerate {
    group = "openapi tools"
    generatorName.set("spring") // see available configOptions: https://openapi-generator.tech/docs/generators/spring
    inputSpec.set("$projectDir/src/main/resources/springdoc/demo-api.yaml")
    outputDir.set(project.layout.buildDirectory.dir("generated-sources").get().toString())
    apiPackage.set("com.example.demo.api")
    modelPackage.set("com.example.demo.api.model")

    configOptions.set(mapOf(
            "interfaceOnly" to "true",
            "skipDefaultInterface" to "true",
            "useSpringBoot3" to "true",
            "annotationLibrary" to "none",
            "documentationProvider" to "none",
            "dateLibrary" to "java8",
            "useTags" to "true",
            "useResponseEntity" to "false",
            "reactive" to "true"
    ))
}

sourceSets {
    main {
        java {
            // TODO: Set this path according to what was generated for you
            srcDir(project.layout.buildDirectory.dir("/generated-sources/src/main/java"))
        }
    }
}

tasks.withType<KaptGenerateStubsTask>().configureEach {
    dependsOn(("openApiGenerate"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    // Spring Boot WebFlux (Reactive API)
    implementation(libs.spring.boot.starter.webflux)

    // R2DBC PostgreSQL (Reactive Database)
    implementation(libs.spring.boot.starter.data.r2dbc)
    implementation(libs.r2dbc.postgresql)
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.postgresql:postgresql:42.7.5")

    // Validation
    implementation(libs.spring.boot.starter.validation)

    // OpenAPI (Swagger)
    compileOnly(libs.jackson.databind.nullable)
    implementation(libs.springdoc.openapi.starter.webflux.ui)

    // MapStruct - Lombok (For Cleaner Code)
    // MapStruct
    implementation(libs.mapstruct)
    kapt(libs.mapstruct.processor)  // Ensure annotation processing works with kapt

    // Lombok (needed for compatibility)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    kapt(libs.lombok)

    // Unit Testing (Spring + JUnit 5 + Testcontainers)
    testImplementation(libs.spring.boot.starter.test) {
        exclude(group = "org.junit.vintage") // Ensure only JUnit 5 is used
    }
    testImplementation(libs.spring.boot.test.autoconfigure)
    testImplementation(libs.spring.boot.testcontainers)

    testImplementation(libs.junit.jupiter)

    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit)
    testImplementation(libs.testcontainers.postgresql)
}