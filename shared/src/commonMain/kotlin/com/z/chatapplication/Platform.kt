package com.z.chatapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform