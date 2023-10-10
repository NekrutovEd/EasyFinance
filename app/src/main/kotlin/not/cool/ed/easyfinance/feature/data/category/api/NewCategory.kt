package not.cool.ed.easyfinance.feature.data.category.api

import not.cool.ed.easyfinance.common.enums.ReceiptType

class NewCategory(
    val title: String,
    val emoji: String,
    val types: Set<ReceiptType>,
)
