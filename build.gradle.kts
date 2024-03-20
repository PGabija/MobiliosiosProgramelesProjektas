buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("androidx.room:room-compiler:2.6.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
        classpath("com.android.tools.build:gradle:7.1.0")
        classpath(kotlin("gradle-plugin", version = "1.9.21"))
    }
}
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}