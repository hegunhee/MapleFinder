plugins {
    id("maplefinder.android.library")
    id("maplefinder.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlinx-serialization")
}

android {
    namespace = "com.hegunhee.maplefinder.data"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:util"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.moshi)
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}
