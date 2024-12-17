import com.hegunhee.maplemfinder.build_logic.setting.configureHilt
import com.hegunhee.maplemfinder.build_logic.setup.libs

plugins {
    id("maplefinder.android.library")
    id("maplefinder.android.compose")
}

configureHilt()

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:util"))
    
    val libs = project.extensions.libs
    implementation(libs.findLibrary("core.ktx").get())
    implementation(libs.findLibrary("appcompat").get())
    implementation(libs.findLibrary("material").get())
    implementation(libs.findLibrary("material3").get())

    implementation(libs.findLibrary("lifecycle-ktx").get())
    implementation(libs.findLibrary("lifecycle-viewmodel").get())
    implementation(libs.findLibrary("lifecycle-compose").get())
    implementation(libs.findLibrary("hilt-navigation").get())
    implementation(libs.findLibrary("activity-compose").get())
    implementation(libs.findLibrary("compose-navigation").get())
    implementation(libs.findLibrary("hilt-navigation-compose").get())

    implementation(libs.findLibrary("coroutine-core").get())
}
