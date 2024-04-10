package com.georgiyshur.muzztask.chat.domain.usecase

import com.georgiyshur.muzztask.chat.domain.UserRepository
import javax.inject.Inject

internal class SwitchUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    fun execute() {
        userRepository.switchUser()
    }
}
