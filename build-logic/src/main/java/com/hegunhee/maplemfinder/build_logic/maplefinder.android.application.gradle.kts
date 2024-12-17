import com.hegunhee.maplemfinder.build_logic.setting.configureComposeAndroid
import com.hegunhee.maplemfinder.build_logic.setting.configureHilt
import com.hegunhee.maplemfinder.build_logic.setting.configureKotlinAndroid
import com.hegunhee.maplemfinder.build_logic.setup.libs

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureComposeAndroid()
configureHilt()

dependencies {

    add("implementation",project(":core:data"))
    add("implementation",project(":core:domain"))

    add("implementation",project(":feature:main"))

    add("implementation",libs.findLibrary("core-ktx").get())
    add("implementation",libs.findLibrary("lifecycle-ktx").get())
    add("implementation",libs.findLibrary("activity-compose").get())
    add("implementation",libs.findLibrary("compose-navigation").get())
    
}
