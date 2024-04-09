package com.georgiyshur.muzztask.chat.domain.usecase

import com.georgiyshur.muzztask.chat.domain.MessagesRepository
import com.georgiyshur.muzztask.chat.domain.NewMessage
import java.time.ZonedDateTime
import javax.inject.Inject

internal class CreateMessageUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository,
) {

    suspend fun execute(
        creatorId: Int,
        text: String,
    ) {
        messagesRepository.createMessage(
            NewMessage(
                createdAt = ZonedDateTime.now(),
                creatorId = creatorId,
                text = text,
            )
        )
    }
}
