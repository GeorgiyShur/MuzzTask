package com.georgiyshur.muzztask.chat.domain

import com.georgiyshur.muzztask.chat.data.LocalDataSource
import com.georgiyshur.muzztask.database.entity.Message
import javax.inject.Inject

internal class MessagesRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) {

    fun getMessagesFlow() = localDataSource.getMessagesFlow()

    suspend fun createMessage(newMessage: NewMessage) {
        localDataSource.createMessage(newMessage.toMessage())
    }

    private fun NewMessage.toMessage(): Message {
        return Message(
            createdAt = createdAt,
            creatorId = creatorId,
            text = text,
        )
    }
}
