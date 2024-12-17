plugins {
    id("maplefinder.android.application")
}

android {
    namespace = "com.hegunhee.maplefinder.maplefinder"

    defaultConfig {
        applicationId = "com.hegunhee.maplefinder.maplefinder"
        versionCode = 1
        versionName = "1.0"

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
