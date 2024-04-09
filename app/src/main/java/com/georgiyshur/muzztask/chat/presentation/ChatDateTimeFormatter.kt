package com.georgiyshur.muzztask.chat.presentation

import android.content.Context
import com.georgiyshur.muzztask.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class ChatDateTimeFormatter {

    fun format(
        context: Context,
        localDateTime: LocalDateTime,
    ): String {
        val formattedTime = localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
        return when {
            localDateTime.isToday() -> context.getString(R.string.today_format, formattedTime)
            localDateTime.isYesterday() -> context.getString(
                R.string.yesterday_format,
                formattedTime
            )

            else -> localDateTime.format(DateTimeFormatter.ofPattern("E HH:mm"))
        }
    }

    private fun LocalDateTime.isToday(): Boolean {
        return toLocalDate().isEqual(LocalDate.now())
    }

    private fun LocalDateTime.isYesterday(): Boolean {
        return toLocalDate().plusDays(1).isEqual(LocalDate.now())
    }
}
