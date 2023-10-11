package not.cool.ed.easyfinance.feature.screen.category.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.feature.featurecase.category.api.SaveNewCategoryFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.SaveNewCategoryResult
import not.cool.ed.easyfinance.feature.screen.category.create.navigation.CategoryCreateDirection
import not.cool.ed.easyfinance.feature.screen.category.create.navigation.CategoryCreateRouter
import javax.inject.Inject

@HiltViewModel
class CategoryCreateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val router: CategoryCreateRouter,
    private val saveNewCategoryFeatureCase: SaveNewCategoryFeatureCase,
) : ViewModel(), CategoryCreateRouter by router {

    private val _newCategory: MutableStateFlow<NewCategoryModel>
    init {
        val receiptType = CategoryCreateDirection.getReceiptType(savedStateHandle)
        val types = if (receiptType != null) setOf(receiptType) else setOf()
        _newCategory = MutableStateFlow(NewCategoryModel("", "\uD83D\uDE00", types))
    }
    val newCategory: StateFlow<NewCategoryModel> = _newCategory

    private val _error: Channel<String> = Channel(capacity = Channel.UNLIMITED)
    val error: ReceiveChannel<String> = _error

    fun close() {
        router.back()
    }

    fun changeEmoji(newEmoji: String) {
        _newCategory.update { it.copy(emoji = newEmoji) }
    }

    fun changeTitle(newTitle: String) {
        _newCategory.update { it.copy(title = newTitle) }
    }

    fun changeType(type: ReceiptType) {
        val newTypes = _newCategory.value.types.toMutableSet().apply {
            if (contains(type)) remove(type) else add(type)
        }
        _newCategory.update { it.copy(types = newTypes) }
    }

    fun saveNewCategory() {
        viewModelScope.launch {
            val category = _newCategory.value
            when (saveNewCategoryFeatureCase(category.title, category.emoji, category.types)) {
                SaveNewCategoryResult.Success -> router.back()
                SaveNewCategoryResult.IsAlreadyExist -> _error.trySend("Категория с таким названием уже существует")
                SaveNewCategoryResult.EmptyTitle -> _error.trySend("Название категории не может быть пустым")
                SaveNewCategoryResult.EmptyTypes -> _error.trySend("Тип категории не может быть пустым")
                SaveNewCategoryResult.FailOnSave -> _error.trySend("Ошибка при сохранении")
            }
        }
    }
}
