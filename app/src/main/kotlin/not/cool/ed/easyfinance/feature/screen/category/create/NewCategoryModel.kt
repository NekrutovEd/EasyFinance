package not.cool.ed.easyfinance.feature.screen.category.create

import not.cool.ed.easyfinance.common.enums.ReceiptType

data class NewCategoryModel(
    val title: String,
    val emoji: String,
    val types: Set<ReceiptType>,
)
