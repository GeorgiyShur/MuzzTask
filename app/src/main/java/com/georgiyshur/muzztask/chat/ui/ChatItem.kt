package com.georgiyshur.muzztask.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.georgiyshur.muzztask.R
import com.georgiyshur.muzztask.chat.presentation.ChatItem
import com.georgiyshur.muzztask.ui.theme.MuzzPalette
import com.georgiyshur.muzztask.ui.theme.Typography

@Composable
internal fun ChatItem(
    color: Color,
    item: ChatItem,
) {
    when (item) {
        is ChatItem.DateTime -> DateTimeChatItem(item)
        is ChatItem.Message -> {
            MessageChatItem(
                color = color,
                item = item,
            )
        }
    }
}

@Composable
private fun DateTimeChatItem(
    item: ChatItem.DateTime,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 8.dp),
        text = item.dateTimeFormatted,
        style = Typography.labelLarge,
        textAlign = TextAlign.Center,
        color = MuzzPalette.CoolGray,
    )
}

@Composable
private fun MessageChatItem(
    color: Color,
    item: ChatItem.Message,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .background(
                    color = if (item.isSentByCurrentUser) color else MuzzPalette.FogGray,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = if (item.isSentByCurrentUser) 1.dp else 20.dp,
                        bottomStart = if (item.isSentByCurrentUser) 20.dp else 1.dp,
                    ),
                )
                .align(if (item.isSentByCurrentUser) CenterEnd else CenterStart),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                text = item.text,
                style = Typography.bodyMedium,
                color = if (item.isSentByCurrentUser) Color.White else Color.Unspecified,
            )
            if (item.isSentByCurrentUser) {
                ReadIndicator(isRead = item.isRead)
            }
        }
    }
}

@Composable
private fun BoxScope.ReadIndicator(isRead: Boolean) {
    Icon(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 4.dp),
        painter = painterResource(if (isRead) R.drawable.ic_double_tick else R.drawable.ic_tick),
        contentDescription = null,
        tint = Color.White,
    )
}
