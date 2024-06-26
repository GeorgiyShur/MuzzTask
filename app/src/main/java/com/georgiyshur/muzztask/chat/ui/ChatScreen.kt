@file:OptIn(ExperimentalMaterial3Api::class)

package com.georgiyshur.muzztask.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgiyshur.muzztask.R
import com.georgiyshur.muzztask.chat.presentation.ChatItem
import com.georgiyshur.muzztask.chat.presentation.ChatViewModel
import com.georgiyshur.muzztask.chat.presentation.ChatViewState
import com.georgiyshur.muzztask.chat.domain.CurrentUser
import com.georgiyshur.muzztask.ui.theme.Typography
import java.time.LocalDateTime

@Composable
internal fun ChatScreen(
    onBack: () -> Unit,
    viewModel: ChatViewModel,
) {
    val viewState by viewModel.viewStateFlow.collectAsState()

    ChatScreen(
        onBack = onBack,
        onSendClick = viewModel::sendMessage,
        onSwitchUserClick = viewModel::switchUser,
        onTextChange = viewModel::onTextChange,
        viewState = viewState,
    )
}

@Composable
private fun ChatScreen(
    onBack: () -> Unit,
    onSendClick: () -> Unit,
    onSwitchUserClick: () -> Unit,
    onTextChange: (String) -> Unit,
    viewState: ChatViewState,
) {
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            TopBar(
                currentUser = viewState.currentUser,
                onBack = onBack,
                onSwitchUserClick = onSwitchUserClick,
            )
        },
        content = { padding ->
            MessagesContent(
                modifier = Modifier.padding(padding),
                chatData = viewState.chatData,
                color = viewState.currentUser.color,
            )
        },
        bottomBar = {
            SendMessageBar(
                color = viewState.currentUser.color,
                onSendClick = onSendClick,
                onTextChange = onTextChange,
                text = viewState.messageText,
            )
        }
    )
}

@Composable
private fun TopBar(
    currentUser: CurrentUser,
    onBack: () -> Unit,
    onSwitchUserClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.shadow(elevation = 8.dp),
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.back),
                    tint = currentUser.color,
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = currentUser.avatar),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = currentUser.name,
                    style = Typography.titleSmall,
                )
            }
        },
        actions = {
            var isDropdownExpanded by remember { mutableStateOf(false) }

            IconButton(
                onClick = { isDropdownExpanded = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_dots),
                    contentDescription = stringResource(id = R.string.menu),
                    tint = Color.Gray,
                )
            }
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false },
            ) {
                Text(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                onSwitchUserClick()
                                isDropdownExpanded = false
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true),
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = stringResource(id = R.string.switch_user),
                    style = Typography.bodyMedium,
                )
            }
        },
    )
}

@Composable
private fun MessagesContent(
    modifier: Modifier,
    chatData: List<ChatItem>,
    color: Color,
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.wrapContentHeight(),
        state = lazyListState,
        reverseLayout = true,
        verticalArrangement = Arrangement.Bottom,
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(chatData) { item ->
            ChatItem(
                color = color,
                item = item,
            )
        }
    }
}

@Composable
@Preview
internal fun ChatScreenPreview() {
    ChatScreen(
        onBack = {},
        onSendClick = {},
        onSwitchUserClick = {},
        onTextChange = {},
        viewState = ChatViewState(
            chatData = listOf(
                ChatItem.DateTime(LocalDateTime.parse("2024-04-09T11:59:00")),
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
                ChatItem.DateTime(LocalDateTime.parse("2024-04-09T14:13:00")),
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
            )
        ),
    )
}
