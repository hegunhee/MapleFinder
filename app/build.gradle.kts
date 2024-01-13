plugins {
    id("maplefinder.android.application")
}

android {
    namespace = "com.hegunhee.maplefinder.maplefinder"

    defaultConfig {
        applicationId = "com.hegunhee.maplefinder.maplefinder"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.ktx)
    implementation(libs.activity.compose)
    implementation(libs.compose.navigation)
}