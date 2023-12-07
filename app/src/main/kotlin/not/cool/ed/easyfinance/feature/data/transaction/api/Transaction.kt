package not.cool.ed.easyfinance.feature.data.transaction.api

import kotlinx.datetime.Instant
import not.cool.ed.easyfinance.common.enums.ReceiptType

class Transaction(
    val id: Long,
    val type: ReceiptType,
    val accountId: Long,
    val categoryId: Long,
    val date: Instant,
    val actorId: Long,
    val amount: Long,
)
