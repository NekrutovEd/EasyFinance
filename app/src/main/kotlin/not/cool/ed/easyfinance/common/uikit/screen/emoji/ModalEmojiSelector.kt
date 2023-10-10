package not.cool.ed.easyfinance.common.uikit.screen.emoji

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView
import kotlinx.coroutines.launch

@Composable
fun ModalEmojiSelector(onSelectEmoji: (String) -> Unit, onClose: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = onClose,
        sheetState = sheetState
    ) {
        AndroidView(
            factory = {
                EmojiPickerView(it).apply {
                    emojiGridColumns = 9
                    setOnEmojiPickedListener { emojiView ->
                        coroutineScope.launch { sheetState.hide() }
                            .invokeOnCompletion {
                                onSelectEmoji(emojiView.emoji)
                                onClose()
                            }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
