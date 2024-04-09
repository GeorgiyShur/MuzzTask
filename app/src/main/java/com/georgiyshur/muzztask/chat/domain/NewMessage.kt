package com.georgiyshur.muzztask.chat.domain

import java.time.ZonedDateTime

internal data class NewMessage(
    val createdAt: ZonedDateTime,
    val creatorId: Int,
    val text: String,
)
