plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kapt)
}

android {
    namespace = "com.di"
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
}

dependencies {
    implementation(Libs.dagger)
    implementation(Libs.activity)
    implementation(Libs.fragment)
    add("kapt", Libs.dagger_compiler)
}