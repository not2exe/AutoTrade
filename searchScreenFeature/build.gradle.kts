plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
}

android {
    namespace = "com.autotrade.searchscreenfeature"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(Libs.activity)
    implementation(Libs.fragment)
    implementation(Libs.compose_ui)
    implementation(Libs.compose_graphics)
    implementation(Libs.compose_material3)
    implementation(Libs.coroutines)
    implementation(platform(Platforms.compose_bom))
    implementation(project("path" to ":common"))
}