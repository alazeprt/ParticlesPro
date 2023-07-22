plugins {
    id("java")
}

group = "com.alazeprt"
version = "1.1"

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    mavenCentral()
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    implementation("org.jetbrains:annotations:24.0.1")
    implementation(project(":common"))
}

tasks.jar {
    archiveBaseName.set("ParticlesPro-Spigot")
    from(project(":common").projectDir.resolve("build/classes/java/main"))
}