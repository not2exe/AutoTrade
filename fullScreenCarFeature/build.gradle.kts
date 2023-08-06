plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kapt)
    id(Plugins.google_service)
}

android {
    namespace = "com.autotrade.fullscreencarfeature"
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
    implementation(Libs.paging_compose)
    implementation(Libs.dagger)
    implementation(platform(Platforms.firebase_bom))
    implementation(Libs.firestore)
    implementation(Libs.nav_fragment)
    add("kapt", Libs.dagger_compiler)
    implementation(project("path" to ":common"))
    implementation(project("path" to ":di"))
}