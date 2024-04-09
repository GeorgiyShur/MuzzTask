package com.georgiyshur.muzztask.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity
internal data class Message(
    @ColumnInfo val createdAt: ZonedDateTime,
    @ColumnInfo val creatorId: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val text: String,
)
