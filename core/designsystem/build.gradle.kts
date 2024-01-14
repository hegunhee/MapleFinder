plugins {
    id("maplefinder.android.library")
    id("maplefinder.android.compose")
}

android {
    namespace = "com.hegunhee.maplefinder.designsystem"

}

dependencies {

    implementation(project(":core:model"))
}