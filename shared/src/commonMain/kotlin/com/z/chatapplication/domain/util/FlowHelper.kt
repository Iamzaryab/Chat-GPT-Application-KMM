package com.z.chatapplication.domain.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

/**
 * Lets Swift collect a Flow, since Swift can't call suspend collectors directly.
 * Pattern adapted from https://stackoverflow.com/questions/64175099/listen-to-kotlin-coroutine-flow-from-ios
 */
fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    /**
     * Collects on the main thread. Returns the [Job] so the caller can cancel it
     * (from deinit on iOS) instead of letting the work outlive the screen.
     */
    fun collectCommon(
        coroutineScope: CoroutineScope? = null, // 'viewModelScope' on Android and 'nil' on iOS
        callback: (T) -> Unit, // callback on each emission
    ): Job {
        return onEach {
            callback(it)
        }.launchIn(coroutineScope ?: CoroutineScope(Dispatchers.Main))
    }
}
