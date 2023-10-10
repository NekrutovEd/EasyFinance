package not.cool.ed.easyfinance.feature.screen.category.create.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.common.navigation.api.Direction

private const val KEY_RECEIPT_TYPE = "receiptType"

object CategoryCreateDirection : Direction("category/create") {

    override val route: String = "$target/{$KEY_RECEIPT_TYPE}"

    override val arguments: List<NamedNavArgument> = listOf(navArgument(KEY_RECEIPT_TYPE) { type = NavType.StringType })

    fun createAction(receiptType: ReceiptType) = createNavAction("$target/${receiptType.name}")

    fun getReceiptType(savedStateHandle: SavedStateHandle): ReceiptType? = savedStateHandle.get<String>(KEY_RECEIPT_TYPE)?.let(ReceiptType::valueOf)
}
