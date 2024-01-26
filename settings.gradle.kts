pluginManagement {
    includeBuild("build-logic")
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


rootProject.name = "MapleFinder"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:designsystem")
include(":core:ui")
include(":feature:main")
include(":feature:dojang-record")
include(":core:util")
