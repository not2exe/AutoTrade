plugins {
    id(Plugins.android_app)
    id(Plugins.kotlin_android)
    id(Plugins.kapt)
}

android {
    namespace = "com.autotrade"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.autotrade"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Libs.core)
    implementation(Libs.lifecycle_runtime_ktx)
    implementation(Libs.activity_compose)
    implementation(platform(Platforms.compose_bom))
    implementation(Libs.compose_ui)
    implementation(Libs.compose_graphics)
    implementation(Libs.compose_material3)
    implementation(Libs.dagger)
    implementation(project("path" to ":di"))
    add("kapt", Libs.dagger_compiler)
}