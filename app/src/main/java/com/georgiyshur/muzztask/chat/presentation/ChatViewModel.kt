package com.georgiyshur.muzztask.chat.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ChatViewModel @Inject constructor() : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(ChatViewState)
    val viewStateFlow get() = _viewStateFlow.asStateFlow()

    // TODO presentation logic
}

internal data object ChatViewState
