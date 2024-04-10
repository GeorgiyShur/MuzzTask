package com.georgiyshur.muzztask.chat.domain.usecase

import com.georgiyshur.muzztask.chat.domain.UserRepository
import javax.inject.Inject

internal class GetCurrentUserFlowUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    fun execute() = userRepository.getCurrentUserFlow()
}
