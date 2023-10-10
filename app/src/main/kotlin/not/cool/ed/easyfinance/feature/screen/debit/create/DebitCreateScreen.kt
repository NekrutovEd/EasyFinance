package not.cool.ed.easyfinance.feature.screen.debit.create

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import kotlinx.coroutines.launch
import not.cool.ed.easyfinance.common.navigation.api.composableTo
import not.cool.ed.easyfinance.common.uikit.atom.spacer.Spacer
import not.cool.ed.easyfinance.common.uikit.molecule.account.AccountItem
import not.cool.ed.easyfinance.common.uikit.molecule.account.AddAccountItem
import not.cool.ed.easyfinance.common.uikit.molecule.category.CategoryItem
import not.cool.ed.easyfinance.feature.screen.debit.create.navigation.DebitCreateDirection
import not.cool.ed.easyfinance.feature.screen.debit.create.selector.CategorySelector
import java.util.Locale


fun NavGraphBuilder.composableToDebitCreate() = composableTo(DebitCreateDirection) { DebitCreateScreen() }

private const val EMOJI_EMPTY = "\uD83E\uDEE5"
private const val DATE_PATTERN = "dd MMM yyyy"

@Composable
fun DebitCreateScreen(viewModel: DebitCreateViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Create debit") }, navigationIcon = {
                IconButton(viewModel::close) {
                    Icon(Icons.Rounded.Close, contentDescription = "Close")
                }
            })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(12.dp)
        ) {

            val scope = rememberCoroutineScope()

            val state by viewModel.state.collectAsState()
            val categories by viewModel.categories.collectAsStateWithLifecycle()
            val accounts by viewModel.accounts.collectAsStateWithLifecycle()

            val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            val datePickerState = rememberDatePickerState(state.date)

            val locale = remember { Locale.getDefault() }

            if (state.showCategorySelector) {
                ModalBottomSheet(onDismissRequest = viewModel::onDismissCategoryModal, sheetState = modalBottomSheetState) {
                    CategorySelector(
                        categories,
                        onAddClick = viewModel::onClickCreateCategory,
                        onSelect = { category ->
                            scope.launch { modalBottomSheetState.hide() }
                                .invokeOnCompletion { viewModel.onSelectedCategory(category) }
                        }
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(text = "Категория")
                CategoryItem(
                    title = state.category?.title ?: "Пусто",
                    emoji = state.category?.emoji ?: EMOJI_EMPTY,
                    modifier = Modifier.size(100.dp),
                    onClick = viewModel::onClickCategory
                )

                Spacer(12.dp)

                Text(text = "Описание")
                TextField(value = state.description, onValueChange = viewModel::onChangedDescription)

                Spacer(12.dp)

                Text(text = "Сумма")
                val decimalAmountTransformation = remember { DecimalAmountTransformation(locale) }
                TextField(
                    value = state.total,
                    onValueChange = { viewModel.onChangedTotal(decimalAmountTransformation.filteredDecimalText(it)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    visualTransformation = decimalAmountTransformation
                )

                Spacer(12.dp)

                Text(text = "Дата")
                val dateFormatter = remember { SimpleDateFormat(DATE_PATTERN, locale) }
                Button(onClick = { viewModel.onClickDate() }) {
                    Text(text = dateFormatter.format(state.date))
                }
                if (state.showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { viewModel.onDismissDatePicker() },
                        confirmButton = {
                            Button(onClick = { viewModel.onChangedDate(datePickerState.selectedDateMillis ?: 0) }) {
                                Text(text = "Применить")
                            }
                        },
                        dismissButton = {
                            FilledTonalButton(onClick = {
                                datePickerState.selectedDateMillis = state.date
                                viewModel.onDismissDatePicker()
                            }) {
                                Text(text = "Отменить")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

                Spacer(12.dp)

                Text(text = "Счет откуда")
                AccountRow(
                    accounts = accounts,
                    selectedAccount = state.from,
                    modifier = Modifier.size(68.dp),
                    onClickAccount = viewModel::onSelectedAccountFrom,
                    onClickCreateAccount = viewModel::onClickCreateAccount
                )

                Spacer(12.dp)

                Text(text = "Счет куда")
                AccountRow(
                    accounts = accounts,
                    selectedAccount = state.to,
                    modifier = Modifier.size(68.dp),
                    onClickAccount = viewModel::onSelectedAccountTo,
                    onClickCreateAccount = viewModel::onClickCreateAccount
                )
            }
        }
    }
}

@Composable
fun AccountRow(
    accounts: List<Account>,
    selectedAccount: Account?,
    modifier: Modifier = Modifier,
    onClickAccount: (Account) -> Unit,
    onClickCreateAccount: () -> Unit
) {
    LazyRow {
        items(accounts) { account ->
            AccountItem(
                title = account.title,
                isSelected = account == selectedAccount,
                modifier = modifier,
            ) {
                onClickAccount(account)
            }
            Spacer(12.dp)
        }
        item {
            AddAccountItem(modifier = modifier) {
                onClickCreateAccount()
            }
        }
    }
}
