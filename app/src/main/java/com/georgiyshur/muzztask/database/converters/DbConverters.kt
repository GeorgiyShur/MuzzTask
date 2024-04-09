package com.georgiyshur.muzztask.database.converters

import androidx.room.TypeConverter
import java.time.ZonedDateTime

class DbConverters {

    @TypeConverter
    fun fromTimestamp(value: String?): ZonedDateTime? {
        return value?.let { ZonedDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: ZonedDateTime?): String? {
        return date?.toString()
    }
}
