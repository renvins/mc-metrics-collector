plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.openapi.generator") version ("7.8.0")
    id("org.liquibase.gradle") version ("2.2.0")
}

repositories {
    mavenCentral()
}

dependencies {
    val jacksonNullableVer = "0.2.6"
    val springdocVer = "2.5.0"
    val influxdbVer = "7.2.0"
    val influxDbMicrometerRegistryVer = "1.12.2"
    val guavaVer = "33.3.0-jre"

    api(project(":api"))

    implementation("org.openapitools:jackson-databind-nullable:$jacksonNullableVer")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVer")
    implementation("com.influxdb:influxdb-spring:$influxdbVer")
    implementation("io.micrometer:micrometer-registry-influx:$influxDbMicrometerRegistryVer")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.google.guava:guava:$guavaVer")

    liquibaseRuntime ("org.liquibase:liquibase-core")
    liquibaseRuntime ("info.picocli:picocli:4.7.5")
    liquibaseRuntime ("org.postgresql:postgresql")

    runtimeOnly("org.postgresql:postgresql")

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

liquibase {
    activities.register("main") {
        this.arguments = mapOf(
            "changeLogFile" to "src/main/resources/db/changelog.sql",
            "url" to "jdbc:postgresql://localhost:5432/postgres?currentSchema=mmc_db",
            "username" to "postgres",
            "password" to "admin",
        )
    }
}

tasks.openApiGenerate {
    inputSpec.set("$rootDir/ingestion-app/src/main/resources/static/api/swagger.yml")
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

tasks.register("migrate") {
    group = "liquibase"
    description = "Run liquibase migrations"
    dependsOn("update")
}

tasks.bootRun {
    dependsOn("migrate")
}
