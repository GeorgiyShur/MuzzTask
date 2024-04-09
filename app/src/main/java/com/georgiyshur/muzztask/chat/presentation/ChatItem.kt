package com.georgiyshur.muzztask.chat.presentation

import java.time.LocalDateTime

internal sealed class ChatItem {

    data class DateTime(
        val localDateTime: LocalDateTime,
    ): ChatItem()

    data class Message(
        val isRead: Boolean,
        val isSentByCurrentUser: Boolean,
        val text: String,
    ): ChatItem()
}
