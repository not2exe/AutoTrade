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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Libs.dagger)
    implementation(Libs.fragment)
    implementation(Libs.nav_fragment)
    implementation(Libs.nav_ui)
    implementation(platform(Platforms.firebase_bom))
    implementation(Libs.firestore)
    implementation(project("path" to ":di"))
    implementation(project("path" to ":searchScreenFeature"))
    implementation(project("path" to ":fullScreenCarFeature"))
    implementation(project("path" to ":common"))
    add("kapt", Libs.dagger_compiler)
}