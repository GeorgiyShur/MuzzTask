@file:OptIn(ExperimentalMaterial3Api::class)

package com.georgiyshur.muzztask.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgiyshur.muzztask.R
import com.georgiyshur.muzztask.chat.presentation.ChatViewModel
import com.georgiyshur.muzztask.chat.presentation.ChatViewState
import com.georgiyshur.muzztask.chat.presentation.CurrentUser
import com.georgiyshur.muzztask.ui.theme.Typography

@Composable
internal fun ChatScreen(
    onBack: () -> Unit,
    viewModel: ChatViewModel,
) {
    val viewState by viewModel.viewStateFlow.collectAsState()

    ChatScreen(
        onBack = onBack,
        onSwitchUserClick = viewModel::switchUser,
        viewState = viewState,
    )
}

@Composable
private fun ChatScreen(
    onBack: () -> Unit,
    onSwitchUserClick: () -> Unit,
    viewState: ChatViewState,
) {
    Scaffold(
        topBar = {
            TopBar(
                currentUser = viewState.currentUser,
                onBack = onBack,
                onSwitchUserClick = onSwitchUserClick,
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
private fun TopBar(
    currentUser: CurrentUser,
    onBack: () -> Unit,
    onSwitchUserClick: () -> Unit,
) {
    TopAppBar(
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
        }
    )
}

@Composable
@Preview
internal fun ChatScreenPreview() {
    ChatScreen(
        onBack = {},
        onSwitchUserClick = {},
        viewState = ChatViewState(),
    )
}
