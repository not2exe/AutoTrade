plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.google_service)
    id(Plugins.kapt)
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
    implementation(Libs.paging)
    implementation(Libs.paging_compose)
    implementation(platform(Platforms.firebase_bom))
    implementation(Libs.firestore)
    implementation(Libs.dagger)
    add("kapt", Libs.dagger_compiler)
    implementation(project("path" to ":common"))
    implementation(project("path" to ":di"))
}