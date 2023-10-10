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
import not.cool.ed.easyfinance.feature.featurecase.account.api.GetAccountsFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.GetDebitCategoriesFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.model.DebitCategory
import not.cool.ed.easyfinance.feature.screen.debit.create.navigation.DebitCreateRouter
import javax.inject.Inject

@HiltViewModel
class DebitCreateViewModel @Inject constructor(
    private val router: DebitCreateRouter,
    private val getDebitCategories: GetDebitCategoriesFeatureCase,
    private val getAccounts: GetAccountsFeatureCase,
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
        _state.update { state -> state.copy(from = account, to = state.to.takeUnless { it == account }) }
    }

    fun onSelectedAccountTo(account: Account) {
        _state.update { state -> state.copy(from = state.from.takeUnless { it == account }, to = account) }
    }

    fun onChangedDescription(newDescription: String) {
        _state.update { it.copy(description = newDescription) }
    }

    fun onChangedTotal(newTotal: String) {
        _state.update { it.copy(total = newTotal) }
    }

    fun onChangedDate(newDate: Long) {
        _state.update { it.copy(date = newDate, showDatePicker = false) }
    }
}
