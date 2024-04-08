package com.georgiyshur.muzztask.chat.presentation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.georgiyshur.muzztask.R
import com.georgiyshur.muzztask.ui.theme.MuzzPalette

internal data class CurrentUser(
    @DrawableRes val avatar: Int,
    val color: Color,
    val id: Int,
    val name: String,
) {

    /*
    I'm using constants just for simplicity for this task. In real application this data should
    be fetched from remote source through API.
     */
    companion object {
        val FEMALE = CurrentUser(
            avatar = R.drawable.img_avatar_female,
            color = MuzzPalette.Pink,
            id = 1,
            name = "Sarah",
        )
        val MALE = CurrentUser(
            avatar = R.drawable.img_avatar_male,
            color = MuzzPalette.Blue,
            id = 2,
            name = "Adnan",
        )
    }
}
