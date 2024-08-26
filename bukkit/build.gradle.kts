repositories {
    mavenLocal()
}

dependencies {
    api(project(":api"))

    compileOnly("org.spigotmc:spigot:1.18-R0.1-SNAPSHOT") /* Getting local spigot jar to get NMS */
}