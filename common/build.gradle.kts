plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kapt)
}

android {
    namespace = "com.autotrade.common"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = Config.javaCompileVersion
        targetCompatibility = Config.javaCompileVersion
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
    }
}
dependencies {
    implementation(Libs.compose_ui)
    implementation(Libs.compose_material3)
    implementation(platform(Platforms.compose_bom))
    implementation(Libs.dagger)
    add("kapt", Libs.dagger_compiler)
    implementation(project("path" to ":di"))
}