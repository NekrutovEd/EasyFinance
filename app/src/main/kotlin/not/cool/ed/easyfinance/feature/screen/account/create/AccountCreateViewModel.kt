package not.cool.ed.easyfinance.feature.screen.account.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import not.cool.ed.easyfinance.feature.featurecase.account.api.SaveNewAccountFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.account.api.SaveNewAccountResult
import not.cool.ed.easyfinance.feature.featurecase.category.api.SaveNewCategoryResult
import not.cool.ed.easyfinance.feature.screen.account.create.navigation.AccountCreateRouter
import javax.inject.Inject

@HiltViewModel
class AccountCreateViewModel @Inject constructor(
    private val router: AccountCreateRouter,
    private val saveNewAccountFeatureCase: SaveNewAccountFeatureCase,
) : ViewModel(), AccountCreateRouter by router {

    private val _newAccount: MutableStateFlow<NewAccountModel> = MutableStateFlow(NewAccountModel(""))
    val newAccount: StateFlow<NewAccountModel> = _newAccount

    private val _error: Channel<String> = Channel()
    val error: ReceiveChannel<String> = _error

    fun close() {
        router.back()
    }

    fun changeTitle(newTitle: String) {
        _newAccount.update { it.copy(title = newTitle) }
    }

    fun saveNewAccount() {
        viewModelScope.launch {
            val category = _newAccount.value
            when (saveNewAccountFeatureCase(category.title)) {
                SaveNewAccountResult.Success -> router.back()
                SaveNewAccountResult.IsAlreadyExist -> _error.trySend("Категория с таким названием уже существует")
                SaveNewAccountResult.EmptyTitle -> _error.trySend("Название категории не может быть пустым")
                SaveNewAccountResult.FailOnSave -> _error.trySend("Ошибка при сохранении")
            }
        }
    }
}
