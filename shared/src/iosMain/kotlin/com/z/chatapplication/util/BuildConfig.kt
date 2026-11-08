package com.z.chatapplication.util

import kotlin.experimental.ExperimentalNativeApi

actual class BuildConfig {
    @OptIn(ExperimentalNativeApi::class)
    actual fun isDebug() = Platform.isDebugBinary
    actual fun isAndroid() = false
}
