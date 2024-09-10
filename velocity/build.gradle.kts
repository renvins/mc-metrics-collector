repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenLocal()
}

dependencies {
    api(project(":api"))
    api(project(":common"))

    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
}