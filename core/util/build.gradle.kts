plugins {
    id("maplefinder.android.library")
    id("maplefinder.hilt")
}

android {
    namespace = "com.hegunhee.maplefinder.util"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}