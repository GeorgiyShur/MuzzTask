package com.georgiyshur.muzztask.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgiyshur.muzztask.chat.domain.usecase.CreateMessageUseCase
import com.georgiyshur.muzztask.chat.domain.usecase.GetMessagesFlowUseCase
import com.georgiyshur.muzztask.chat.presentation.CurrentUser.Companion.FEMALE
import com.georgiyshur.muzztask.chat.presentation.CurrentUser.Companion.MALE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ChatViewModel @Inject constructor(
    chatDataConverter: ChatDataConverter,
    private val createMessage: CreateMessageUseCase,
    getMessagesFlow: GetMessagesFlowUseCase,
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(ChatViewState())
    val viewStateFlow get() = _viewStateFlow.asStateFlow()

    init {
        getMessagesFlow.execute()
            .onEach { messages ->
                updateState {
                    copy(
                        chatData = chatDataConverter.convertMessages(
                            currentUserId = viewStateFlow.value.currentUser.id,
                            messages = messages,
                        )
                    )
                }
            }
            .launchIn(viewModelScope)
    }

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
        viewModelScope.launch {
            createMessage.execute(
                creatorId = viewStateFlow.value.currentUser.id,
                text = viewStateFlow.value.messageText,
            )
            updateState { copy(messageText = "") }
        }
    }

    private fun updateState(updater: ChatViewState.() -> ChatViewState) {
        _viewStateFlow.update(updater)
    }
}

internal data class ChatViewState(
    val currentUser: CurrentUser = FEMALE,
    val chatData: List<ChatItem> = emptyList(),
    val messageText: String = "",
)
