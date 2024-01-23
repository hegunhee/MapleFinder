plugins {
    id("maplefinder.android.feature")
}

android {
    namespace = "com.hegunhee.maplefinder.main"

}

dependencies {
    implementation(project(":feature:dojang-record"))
}