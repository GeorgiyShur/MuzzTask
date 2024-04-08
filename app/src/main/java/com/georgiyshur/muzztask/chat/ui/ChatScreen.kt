@file:OptIn(ExperimentalMaterial3Api::class)

package com.georgiyshur.muzztask.chat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.georgiyshur.muzztask.chat.presentation.ChatViewModel
import com.georgiyshur.muzztask.chat.presentation.ChatViewState

@Composable
internal fun ChatScreen(
    onBack: () -> Unit,
    viewModel: ChatViewModel,
) {
    val viewState by viewModel.viewStateFlow.collectAsState()

    ChatScreen(
        onBack = onBack,
        viewState = viewState,
    )
}

@Composable
internal fun ChatScreen(
    onBack: () -> Unit,
    viewState: ChatViewState,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                title = {
                    // TODO add user name and avatar
                }
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            // TODO chat UI
        }
    }
}

@Composable
@Preview
internal fun ChatScreenPreview() {
    ChatScreen(
        onBack = {},
        viewState = ChatViewState,
    )
}
