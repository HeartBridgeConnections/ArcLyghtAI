pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // 👈 this line is required
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ArcLyghtAI"
include(":app")
