package com.georgiyshur.muzztask.chat.data

import com.georgiyshur.muzztask.database.dao.MessageDao
import com.georgiyshur.muzztask.database.entity.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal interface LocalDataSource {
    fun getMessagesFlow(): Flow<List<Message>>
    suspend fun createMessage(message: Message)
}

internal class DatabaseDataSource @Inject constructor(
    private val messageDao: MessageDao
) : LocalDataSource {

    override fun getMessagesFlow(): Flow<List<Message>> {
        return messageDao.getAll()
    }

    override suspend fun createMessage(message: Message) {
        messageDao.insert(message)
    }
}
