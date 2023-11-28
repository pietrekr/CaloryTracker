plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "pl.programming.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.onboardingDomain))
}