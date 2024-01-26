plugins {
    id("maplefinder.android.library")
    id("maplefinder.android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.moshi)
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}