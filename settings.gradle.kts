pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //maven{setUrl("https://oss.sonatype.org/content/repositories/snapshots/")}
        maven{setUrl ("https://oss.sonatype.org/content/repositories/snapshots/")}
    }
}

rootProject.name = "Foodies"
include(":app")
include(":domain")
include(":data")
include(":entity")
