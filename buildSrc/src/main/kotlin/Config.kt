import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 24
    const val targetSdk = 33
    const val compileSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "17"
    val javaCompileVersion = JavaVersion.VERSION_17
}