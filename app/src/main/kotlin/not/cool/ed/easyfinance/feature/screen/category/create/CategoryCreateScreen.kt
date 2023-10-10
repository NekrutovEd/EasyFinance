package not.cool.ed.easyfinance.feature.screen.category.create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import kotlinx.coroutines.channels.consumeEach
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.common.navigation.api.composableTo
import not.cool.ed.easyfinance.common.uikit.molecule.category.CategoryItem
import not.cool.ed.easyfinance.common.uikit.atom.chip.SwitchChip
import not.cool.ed.easyfinance.common.uikit.screen.emoji.ModalEmojiSelector
import not.cool.ed.easyfinance.common.uikit.atom.spacer.Spacer
import not.cool.ed.easyfinance.feature.screen.category.create.navigation.CategoryCreateDirection


fun NavGraphBuilder.composableToCategoryCreate() = composableTo(CategoryCreateDirection) { CategoryCreateScreen() }

@Composable
fun CategoryCreateScreen(viewModel: CategoryCreateViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Создание категории") }, navigationIcon = {
                IconButton(viewModel::close) {
                    Icon(Icons.Rounded.Close, contentDescription = "Close")
                }
            })
        }
    ) { padding ->

        val context = LocalContext.current
        LaunchedEffect(viewModel) {
            viewModel.error.consumeEach {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        val category: NewCategoryModel by viewModel.newCategory.collectAsState()

        var showIconSelector by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.size(100.dp)) {
                    CategoryItem(category.title, category.emoji) {
                        showIconSelector = true
                    }
                }
            }
            Spacer(12.dp)
            Text(text = "Название")
            Spacer(4.dp)
            TextField(
                value = category.title,
                onValueChange = viewModel::changeTitle,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(12.dp)
            Text(text = "Типы")
            LazyRow(Modifier.fillMaxWidth()) {
                items(ReceiptType.values()) { type ->
                    SwitchChip(type.name, category.types.contains(type), Modifier.padding(4.dp)) {
                        viewModel.changeType(type)
                    }
                }
            }
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
                Button(onClick = { viewModel.saveNewCategory() }) {
                    Text(text = "Сохранить")
                }
            }

            if (showIconSelector) {
                ModalEmojiSelector(
                    onSelectEmoji = { emoji ->
                        viewModel.changeEmoji(emoji)
                    },
                    onClose = {
                        showIconSelector = false
                    }
                )
            }
        }
    }
}
