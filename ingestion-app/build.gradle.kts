plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.openapi.generator") version ("7.8.0")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":api"))
// https://mvnrepository.com/artifact/org.openapitools/jackson-databind-nullable
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generate-resources/main/src/main/java")
        }
    }
}

tasks.openApiGenerate {
    inputSpec.set("$rootDir/ingestion-app/src/main/resources/api/swagger.yml")
    generatorName.set("spring")
    library.set("spring-boot")
    apiPackage.set("org.github.mcmetricscollector.gen.api")
    modelPackage.set("org.github.mcmetricscollector.gen.model")
    apiNameSuffix.set("Api")
    cleanupOutput.set(true)
    additionalProperties.set(
        mapOf(
            "useTags" to "true",
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "documentationProvider" to "none",
            "annotationLibrary" to "none"
        )
    )
}

tasks.openApiGenerate {
    mustRunAfter("generateEffectiveLombokConfig")
}

tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
}
