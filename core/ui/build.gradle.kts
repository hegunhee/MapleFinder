plugins {
    id("maplefinder.android.library")
    id("maplefinder.android.compose")
}

android {
    namespace = "com.hegunhee.maplefinder.ui"
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
}