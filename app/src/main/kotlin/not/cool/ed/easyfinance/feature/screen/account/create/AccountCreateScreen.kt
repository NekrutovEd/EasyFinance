package not.cool.ed.easyfinance.feature.screen.account.create

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
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.common.navigation.api.composableTo
import not.cool.ed.easyfinance.common.uikit.molecule.category.CategoryItem
import not.cool.ed.easyfinance.common.uikit.atom.spacer.Spacer
import not.cool.ed.easyfinance.feature.screen.account.create.navigation.AccountCreateDirection


fun NavGraphBuilder.composableToAccountCreate() = composableTo(AccountCreateDirection) { AccountCreateScreen() }

@Composable
fun AccountCreateScreen(viewModel: AccountCreateViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Создание счета") }, navigationIcon = {
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

        val category: NewAccountModel by viewModel.newAccount.collectAsState()

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(text = "Название")
            Spacer(4.dp)
            TextField(
                value = category.title,
                onValueChange = viewModel::changeTitle,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(12.dp)
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
                Button(onClick = { viewModel.saveNewAccount() }) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}
