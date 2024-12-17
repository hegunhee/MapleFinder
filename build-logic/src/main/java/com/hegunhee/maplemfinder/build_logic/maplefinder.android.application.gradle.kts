import com.hegunhee.maplemfinder.build_logic.setting.configureComposeAndroid
import com.hegunhee.maplemfinder.build_logic.setting.configureHilt
import com.hegunhee.maplemfinder.build_logic.setting.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureComposeAndroid()
configureHilt()
