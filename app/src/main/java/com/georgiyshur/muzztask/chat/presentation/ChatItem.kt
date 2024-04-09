package com.georgiyshur.muzztask.chat.presentation

internal sealed class ChatItem {

    data class DateTime(
        val dateTimeFormatted: String,
    ): ChatItem()

    data class Message(
        val isRead: Boolean,
        val isSentByCurrentUser: Boolean,
        val text: String,
    ): ChatItem()
}
