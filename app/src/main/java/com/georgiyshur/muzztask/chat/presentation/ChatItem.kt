package com.georgiyshur.muzztask.chat.presentation

import java.time.LocalDateTime

internal sealed class ChatItem {

    /**
     * Represents the section title with the first message's date-time. It has to be
     * displayed if previous message is sent more than one hour ago.
     */
    data class DateTime(
        val localDateTime: LocalDateTime,
    ): ChatItem()

    /**
     * Represents the message item encased in a bubble.
     */
    data class Message(
        val isRead: Boolean,
        val isSentByCurrentUser: Boolean,
        val text: String,
    ): ChatItem()

    /**
     * Adds extra spacing between messages if they are sent by different users or
     * if more than 20 seconds have been passed between messages.
     */
    data object ExtraSpacing: ChatItem()
}
