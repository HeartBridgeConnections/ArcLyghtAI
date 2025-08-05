// Root-level build.gradle.kts

buildscript {
    dependencies {
        // Firebase / Google Services plugin
        classpath("com.google.gms:google-services:4.4.0")
    }

    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    // Optional: Kotlin plugin for root project (if using multi-module builds)
    kotlin("jvm") version "1.9.10" apply false
    id("com.android.application") version "8.12.0" apply false
    id("com.android.library") version "8.12.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
