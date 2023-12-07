package not.cool.ed.easyfinance.feature.screen.debit.create

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import not.cool.ed.easyfinance.feature.featurecase.category.api.model.DebitCategory

data class DebitCreateState(

    val category: DebitCategory? = null,
    val amount: String = "",
    val date: Instant = Clock.System.now(),
    val from: Account? = null,
    val to: Account? = null,

    val showCategorySelector: Boolean = false,
    val showDatePicker: Boolean = false,
)

data class Account(
    val id: Long,
    val title: String,
)
