pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Tmdb Arch Sample"
include(":app")
include(":network")
include(":network:tmdb-service")
include(":feature:trending")
include(":core:model")
include(":core:arch-component")
include(":core:util")
include(":feature:medialist")
include(":core:composables")
