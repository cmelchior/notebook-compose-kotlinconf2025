pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    kotlin("jvm").version(extra["kotlin.version"] as String) apply false
    id("org.jetbrains.compose").version(extra["compose.version"] as String) apply false
    id("org.jetbrains.kotlin.plugin.compose").version(extra["kotlin.version"] as String) apply false
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

rootProject.name = "Compose-Notebook Example"
include(":compose-example-project")
