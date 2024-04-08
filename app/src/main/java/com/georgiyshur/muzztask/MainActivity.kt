package com.georgiyshur.muzztask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.georgiyshur.muzztask.chat.presentation.ChatViewModel
import com.georgiyshur.muzztask.chat.ui.ChatScreen
import com.georgiyshur.muzztask.ui.theme.MuzzTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /*
    In real-world application I'd provide the ViewModel to the screen from the navigation tree with
    hiltViewModel extension function (Hilt Compose Navigation library). This is just a quick
    workaround to get things going.
    */
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuzzTaskTheme {
                ChatScreen(
                    /*
                    We're just closing the app on back press. In real-world application this should
                    be put inside a navigation tree and call back navigation on nav controller.
                     */
                    onBack = { finish() },
                    viewModel = viewModel,
                )
            }
        }
    }
}
