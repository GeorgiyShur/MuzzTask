package com.georgiyshur.muzztask.chat.domain.usecase

import com.georgiyshur.muzztask.chat.domain.MessagesRepository
import javax.inject.Inject

internal class GetMessagesFlowUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository,
) {

    fun execute() = messagesRepository.getMessagesFlow()
}
