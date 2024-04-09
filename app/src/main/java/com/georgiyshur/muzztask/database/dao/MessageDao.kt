package com.georgiyshur.muzztask.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.georgiyshur.muzztask.database.entity.Message
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MessageDao {

    @Query("SELECT * FROM message ORDER BY createdAt")
    fun getAll(): Flow<List<Message>>

    @Insert
    suspend fun insert(message: Message)
}
