package com.hegunhee.maplemfinder.build_logic.setup

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.Project

internal val Project.applicationExtension: CommonExtension<*, *, *, *, *, *>
    get() = extensions.getByType<ApplicationExtension>()

internal val Project.libraryExtension: CommonExtension<*, *, *, *,*,*>
    get() = extensions.getByType<LibraryExtension>()

internal val Project.androidExtension: CommonExtension<*, *, *, *,*,*>
    get() = runCatching { libraryExtension }
        .recoverCatching { applicationExtension }
        .onFailure { println("Could not find Library or Application extension from this project") }
        .getOrThrow()

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
