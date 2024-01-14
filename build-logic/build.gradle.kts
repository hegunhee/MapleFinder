plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.hiltGradlePlugin)
}

gradlePlugin {
    plugins {
        register("HiltAndroid") {
            id = "maplefinder.android.hilt"
            implementationClass = "com.hegunhee.maplemfinder.build_logic.setting.HiltAndroidPlugin"
        }
        register("HiltKotlin") {
            id = "maplefinder.kotlin.hilt"
            implementationClass = "com.hegunhee.maplemfinder.build_logic.setting.HiltKotlinPlugin"
        }
    }
}