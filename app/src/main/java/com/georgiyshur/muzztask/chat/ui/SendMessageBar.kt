package com.georgiyshur.muzztask.chat.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.georgiyshur.muzztask.R
import com.georgiyshur.muzztask.ui.theme.Typography

@Composable
internal fun SendMessageBar(
    color: Color,
    onSendClick: () -> Unit,
    onTextChange: (String) -> Unit,
    text: String,
) {
    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            val isFocused by interactionSource.collectIsFocusedAsState()

            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(minHeight = 40.dp),
                value = text,
                onValueChange = onTextChange,
                maxLines = 3,
                textStyle = Typography.bodyMedium,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(20.dp),
                                color = if (isFocused) color else Color.LightGray
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                modifier = Modifier.size(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    disabledContainerColor = color.copy(alpha = 0.3f),
                ),
                onClick = onSendClick,
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                enabled = text.isNotBlank(),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = stringResource(id = R.string.send),
                    tint = Color.White,
                )
            }
        }
    }
}