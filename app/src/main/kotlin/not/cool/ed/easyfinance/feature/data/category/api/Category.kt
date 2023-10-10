package not.cool.ed.easyfinance.feature.data.category.api

import not.cool.ed.easyfinance.common.enums.ReceiptType

class Category(
    val id: Long,
    val title: String,
    val emoji: String,
    val types: Set<ReceiptType>,
)
