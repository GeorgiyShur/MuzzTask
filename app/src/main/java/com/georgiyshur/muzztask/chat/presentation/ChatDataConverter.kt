package com.georgiyshur.muzztask.chat.presentation

import com.georgiyshur.muzztask.database.entity.Message
import java.time.Duration
import java.time.ZoneId
import javax.inject.Inject

internal class ChatDataConverter @Inject constructor() {

    fun convertMessages(
        currentUserId: Int,
        messages: List<Message>,
    ): List<ChatItem> {
        val chatItems: MutableList<ChatItem> = mutableListOf()
        var lastMessage: Message? = null
        messages.forEach { message ->
            if (
                lastMessage == null ||
                Duration.between(lastMessage!!.createdAt, message.createdAt) > Duration.ofHours(1)
            ) {
                chatItems.add(
                    ChatItem.DateTime(
                        message.createdAt.withZoneSameInstant(ZoneId.systemDefault())
                            .toLocalDateTime()
                    )
                )
            }
            chatItems.add(message.toChatItem(currentUserId))
            lastMessage = message
        }
        return chatItems.toList().reversed()
    }

    private fun Message.toChatItem(
        currentUserId: Int
    ): ChatItem.Message {
        return ChatItem.Message(
            isRead = true,
            isSentByCurrentUser = currentUserId == creatorId,
            text = text,
        )
    }
}
