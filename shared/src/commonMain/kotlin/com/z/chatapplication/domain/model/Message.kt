package com.z.chatapplication.domain.model

data class Message(
    val index: Int,
    val from: Int,
    val message: String,
    val createAt: Long,
) {
    companion object {
        /** Sent by the person using the app. */
        const val FROM_USER = 0
        /** Sent by the model, or a template suggestion shown as if it were. */
        const val FROM_ASSISTANT = 1
    }
}
