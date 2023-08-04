internal object Version {
    const val core_version = "1.10.1"
    const val lifecycle_runtime_ktx_version = "2.6.1"
    const val activity_compose_version = "1.7.2"
    const val compose_bom_version = "2023.03.00"
    const val firebase_bom_version = "32.2.0"
}

object Libs {
    const val core = "androidx.core:core-ktx:${Version.core_version}"
    const val lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle_runtime_ktx_version}"
    const val activity_compose =
        "androidx.activity:activity-compose:${Version.activity_compose_version}"
    const val compose_ui = "androidx.compose.ui:ui"
    const val compose_graphics = "androidx.compose.ui:ui-graphics"
    const val compose_material3 = "androidx.compose.material3:material3"
}

object Platforms {
    const val compose_bom = "androidx.compose:compose-bom:${Version.compose_bom_version}"
    const val firebase_bom = "com.google.firebase:firebase-bom:${Version.firebase_bom_version}"
}

object Plugins {
    const val google_service = "com.google.gms.google-services"
    const val android_app = "com.android.application"
    const val kotlin_android = "org.jetbrains.kotlin.android"
    const val kapt = "org.jetbrains.kotlin.kapt"
}