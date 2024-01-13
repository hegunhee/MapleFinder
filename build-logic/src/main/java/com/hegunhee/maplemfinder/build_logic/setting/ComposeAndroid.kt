package com.hegunhee.maplemfinder.build_logic.setting

import com.hegunhee.maplemfinder.build_logic.setup.androidExtension
import com.hegunhee.maplemfinder.build_logic.setup.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureComposeAndroid() {
    val libs = extensions.libs
    androidExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", libs.findLibrary("material3").get())
            add("implementation",libs.findBundle("compose-ui").get())
            add("androidTestImplementation", libs.findLibrary("ext-junit").get())
            add("androidTestImplementation", libs.findLibrary("espresso-core").get())
            add("androidTestImplementation", libs.findLibrary("compose-junit").get())
            add("debugImplementation", libs.findLibrary("ui-tooling").get())
            add("debugImplementation", libs.findLibrary("ui-test-manifest").get())
        }
    }
}