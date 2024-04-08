package com.georgiyshur.muzztask.chat.presentation

import androidx.lifecycle.ViewModel
import com.georgiyshur.muzztask.chat.presentation.CurrentUser.Companion.FEMALE
import com.georgiyshur.muzztask.chat.presentation.CurrentUser.Companion.MALE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ChatViewModel @Inject constructor() : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(ChatViewState())
    val viewStateFlow get() = _viewStateFlow.asStateFlow()

    fun switchUser() {
        updateState {
            copy(
                currentUser = if (currentUser == FEMALE) MALE else FEMALE,
                messageText = "",
            )
        }
    }

    fun onTextChange(text: String) {
        updateState { copy(messageText = text) }
    }

    fun sendMessage() {
        // TODO add sending logic
    }

    private fun updateState(updater: ChatViewState.() -> ChatViewState) {
        _viewStateFlow.update(updater)
    }
}

internal data class ChatViewState(
    val currentUser: CurrentUser = FEMALE,
    val messageText: String = "",
)
