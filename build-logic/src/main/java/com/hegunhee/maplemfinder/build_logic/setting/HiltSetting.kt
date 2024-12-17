package com.hegunhee.maplemfinder.build_logic.setting

import com.hegunhee.maplemfinder.build_logic.setup.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.kapt")
    }
    val libs = extensions.libs
    dependencies {
        "kapt"(libs.findLibrary("hilt.compiler").get())
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        dependencies {
            "implementation"(libs.findLibrary("hilt.core").get())
        }
    }

    pluginManager.withPlugin("com.android.base") {
        pluginManager.apply("dagger.hilt.android.plugin")
        dependencies {
            "implementation"(libs.findLibrary("hilt.android").get())
        }
    }

}

class HiltPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureHilt()
        }
    }
}
