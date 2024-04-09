package com.georgiyshur.muzztask.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.georgiyshur.muzztask.chat.presentation.CurrentUser.Companion.FEMALE
import com.georgiyshur.muzztask.chat.presentation.CurrentUser.Companion.MALE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ChatViewModel @Inject constructor() : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(ChatViewState())
    val viewStateFlow get() = _viewStateFlow.asStateFlow()

    val chatPagingDataFlow: Flow<PagingData<ChatItem>> by lazy {
        // TODO get data from Room DB
        flowOf(
            PagingData.from(
                listOf(
                    ChatItem.DateTime("Thursday 11:59"),
                    ChatItem.Message(
                        isRead = true,
                        isSentByCurrentUser = false,
                        text = "Hello, how is it going?",
                    ),
                    ChatItem.Message(
                        isRead = true,
                        isSentByCurrentUser = true,
                        text = "Hiii, this is a long pre-populated message to showcase sectioning",
                    ),
                    ChatItem.DateTime("Thursday 17:45"),
                    ChatItem.Message(
                        isRead = false,
                        isSentByCurrentUser = false,
                        text = "Sure, when do you want to go?",
                    ),
                    ChatItem.Message(
                        isRead = false,
                        isSentByCurrentUser = true,
                        text = "Idk, how about tomorrow morning?",
                    ),
                ).reversed()
            )
        )
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
