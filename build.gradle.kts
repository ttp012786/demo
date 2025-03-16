plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("kapt") version "1.9.0"

    id("org.openapi.generator") version "7.12.0"

    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

group = "com.example"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot WebFlux (Reactive API)
    implementation(libs.spring.boot.starter.webflux)

    // R2DBC PostgreSQL (Reactive Database)
    implementation(libs.spring.boot.starter.data.r2dbc)
    implementation(libs.r2dbc.postgresql)

    // Validation
    implementation(libs.spring.boot.starter.validation)

    // OpenAPI (Swagger)
    implementation(libs.springdoc.openapi.starter.webflux.ui)

    // MapStruct (DTO Mapping)
    implementation(libs.mapstruct)
    kapt(libs.mapstruct.processor)

    // Lombok (For Cleaner Code)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)

    // Unit Testing (JUnit 5 + Testcontainers)
    testImplementation(libs.spring.boot.starter.test) {
        exclude(group = "org.junit.vintage") // Ensure only JUnit 5 is used
    }
    testImplementation(libs.spring.boot.test.autoconfigure)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit)
    testImplementation(libs.testcontainers.postgresql)
}

tasks.register<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("generateAppInterfaceAPI") {
    group = "openapi tools"
    generatorName.set("spring") // see available configOptions: https://openapi-generator.tech/docs/generators/spring
    inputSpec.set("$projectDir/src/main/resources/springdoc/demo-api.yaml")
    outputDir.set(project.layout.buildDirectory.dir("generated-sources").get().toString())
    apiPackage.set("com.demo.generated.api")
    modelPackage.set("com.demo.generated.model")
    ignoreFileOverride.set("$projectDir/.openapi-generator-ignore")

    configOptions.set(mapOf(
            "interfaceOnly" to "true",
            "skipDefaultInterface" to "true",
            "useSpringBoot3" to "true",
            "annotationLibrary" to "none",
            "documentationProvider" to "none",
            "dateLibrary" to "java8",
            "useTags" to "true",
            "useResponseEntity" to "false"
    ))
}

tasks.withType<Test> {
    useJUnitPlatform()
}