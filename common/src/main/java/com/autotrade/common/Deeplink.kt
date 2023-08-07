package com.autotrade.common

import android.net.Uri

enum class Deeplink(val deepLink: Uri) {
    NAVIGATE_TO_REDACT_CAR_FRAGMENT(
        Uri.parse("android-app://autotrade/carRedactFragment")
    )
}