plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "pl.programming.tracker_domain"
}

dependencies {
    implementation(project(Modules.core))
}