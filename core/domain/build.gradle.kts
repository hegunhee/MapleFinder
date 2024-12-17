plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("maplefinder.hilt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    implementation(project(":core:model"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutine.core)
}
