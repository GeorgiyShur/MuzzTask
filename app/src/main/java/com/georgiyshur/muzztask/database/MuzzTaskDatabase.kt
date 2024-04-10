package com.georgiyshur.muzztask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.georgiyshur.muzztask.database.converters.DbConverters
import com.georgiyshur.muzztask.database.dao.MessageDao
import com.georgiyshur.muzztask.database.entity.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

@Database(entities = [Message::class], version = 1)
@TypeConverters(DbConverters::class)
internal abstract class MuzzTaskDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        private var INSTANCE: MuzzTaskDatabase? = null

        fun getInstance(context: Context): MuzzTaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MuzzTaskDatabase::class.java,
                    "MuzzTaskDatabase",
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                listOf(
                                    Message(
                                        createdAt = ZonedDateTime.parse("2024-04-09T14:13:00Z"),
                                        creatorId = 1,
                                        text = "Hello, how is it going?",
                                    ),
                                    Message(
                                        createdAt = ZonedDateTime.parse("2024-04-09T14:25:00Z"),
                                        creatorId = 2,
                                        text = "Hiii, there is a couple of pre-populated message to showcase sectioning. A long message to test multiline.",
                                    ),
                                    Message(
                                        createdAt = ZonedDateTime.parse("2024-04-09T19:54:00Z"),
                                        creatorId = 1,
                                        text = "Nice to hear that, when do you wanna go?",
                                    ),
                                    Message(
                                        createdAt = ZonedDateTime.parse("2024-04-09T19:57:00Z"),
                                        creatorId = 2,
                                        text = "Idk, how about tomorrow morning?",
                                    ),
                                ).forEach {
                                    database.messageDao().insert(it)
                                }
                            }
                        }
                    }
                }).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
