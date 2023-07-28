rootProject.name = "ParticlesPro"
include("common")
include("spigot")
pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        gradlePluginPortal()
    }
}
include("fabric")
include("fabric")
