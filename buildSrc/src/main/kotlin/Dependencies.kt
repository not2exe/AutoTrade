internal object Version {
    const val core_version = "1.10.1"
    const val lifecycle_runtime_ktx_version = "2.6.1"
    const val activity_compose_version = "1.7.2"
    const val compose_bom_version = "2023.06.01"
    const val firebase_bom_version = "32.2.0"
    const val jetpack_nav_version = "2.6.0"
    const val dagger_version = "2.47"
    const val fragment_version = "1.6.1"
    const val activity_version = "1.7.2"
    const val coroutines_version = "1.7.3"
    const val paging_version = "3.1.1"
    const val paging_compose_version = "3.2.0"
}

object Libs {
    const val core = "androidx.core:core-ktx:${Version.core_version}"
    const val lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle_runtime_ktx_version}"
    const val activity_compose =
        "androidx.activity:activity-compose:${Version.activity_compose_version}"
    const val compose_ui = "androidx.compose.ui:ui"
    const val compose_graphics = "androidx.compose.ui:ui-graphics"
    const val compose_material3 =
        "androidx.compose.material3:material3"
    const val compose_runtime = "androidx.compose.runtime:runtime"
    const val dagger = "com.google.dagger:dagger:${Version.dagger_version}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Version.dagger_version}"
    const val fragment = "androidx.fragment:fragment-ktx:${Version.fragment_version}"
    const val activity = "androidx.activity:activity-ktx:${Version.activity_version}"
    const val nav_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Version.jetpack_nav_version}"
    const val nav_ui = "androidx.navigation:navigation-ui-ktx:${Version.jetpack_nav_version}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines_version}"
    const val paging = "androidx.paging:paging-common:${Version.paging_version}"
    const val paging_compose = "androidx.paging:paging-compose:${Version.paging_compose_version}"
    const val firestore = "com.google.firebase:firebase-firestore-ktx"
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
    const val android_library = "com.android.library"
}