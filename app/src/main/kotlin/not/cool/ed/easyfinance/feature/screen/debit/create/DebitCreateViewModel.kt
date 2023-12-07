package not.cool.ed.easyfinance.feature.screen.debit.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Instant
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.feature.featurecase.account.api.GetAccountsFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.GetDebitCategoriesFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.model.DebitCategory
import not.cool.ed.easyfinance.feature.featurecase.transaction.api.NewTransaction
import not.cool.ed.easyfinance.feature.featurecase.transaction.api.SaveNewTransactionFeatureCase
import not.cool.ed.easyfinance.feature.screen.debit.create.navigation.DebitCreateRouter
import javax.inject.Inject

@HiltViewModel
class DebitCreateViewModel @Inject constructor(
    private val router: DebitCreateRouter,
    private val getDebitCategories: GetDebitCategoriesFeatureCase,
    private val getAccounts: GetAccountsFeatureCase,
    private val saveTransaction: SaveNewTransactionFeatureCase,
) : ViewModel(), DebitCreateRouter by router {

    private val _state: MutableStateFlow<DebitCreateState> = MutableStateFlow(DebitCreateState())
    val state: StateFlow<DebitCreateState> = _state

    val categories: StateFlow<List<DebitCategory>> = flow { emit(getDebitCategories()) }
        .onEach { if (it.isEmpty()) toCreatingCategory() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    val accounts: StateFlow<List<Account>> = flow { emit(getAccounts()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    fun close() {
        router.back()
    }

    fun onClickCreateCategory() {
        router.toCreatingCategory()
    }

    fun onClickCreateAccount() {
        router.toCreatingAccount()
    }

    fun onClickCategory() {
        _state.update { it.copy(showCategorySelector = true) }
    }

    fun onClickDate() {
        _state.update { it.copy(showDatePicker = true) }
    }

    fun onClickSave() {
            val newTransaction = with(_state.value) {
                NewTransaction(
                    receiptType = ReceiptType.Debit,
                    accountId = to?.id ?: error("Категория не может быть пустой"),
                    categoryId = category?.id ?: error("Категория не может быть пустой"),
                    date = date,
                    actorId = from?.id ?: error("Отправитель не может быть пустой"),
                    amount = amount.toLong(),
                )
            }
            saveTransaction(newTransaction)
    }

    fun onDismissCategoryModal() {
        _state.update { it.copy(showCategorySelector = false) }
    }

    fun onDismissDatePicker() {
        _state.update { it.copy(showDatePicker = false) }
    }

    fun onSelectedCategory(category: DebitCategory) {
        _state.update { it.copy(category = category, showCategorySelector = false) }
    }

    fun onSelectedAccountFrom(account: Account) {
        _state.update { state ->
            state.copy(
                from = account,
                to = state.to.takeUnless { it == account }
            )
        }
    }

    fun onSelectedAccountTo(account: Account) {
        _state.update { state ->
            state.copy(
                from = state.from.takeUnless { it == account },
                to = account
            )
        }
    }

    fun onChangedAmount(newAmount: String) {
        _state.update { it.copy(amount = newAmount) }
    }

    fun onChangedDate(newDate: Instant) {
        _state.update { it.copy(date = newDate, showDatePicker = false) }
    }
}
