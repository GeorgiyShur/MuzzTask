package com.georgiyshur.muzztask.chat.domain

import com.georgiyshur.muzztask.chat.domain.CurrentUser.Companion.FEMALE
import com.georgiyshur.muzztask.chat.domain.CurrentUser.Companion.MALE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepository @Inject constructor() {

    private val currentUserStateFlow = MutableStateFlow(FEMALE)

    fun getCurrentUserFlow(): Flow<CurrentUser> = currentUserStateFlow.asStateFlow()

    fun switchUser() {
        currentUserStateFlow.value = if (currentUserStateFlow.value == FEMALE) MALE else FEMALE
    }
}
