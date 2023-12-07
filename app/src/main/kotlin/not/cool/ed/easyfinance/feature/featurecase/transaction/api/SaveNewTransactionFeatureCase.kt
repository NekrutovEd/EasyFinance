package not.cool.ed.easyfinance.feature.featurecase.transaction.api

import kotlinx.datetime.Instant
import not.cool.ed.easyfinance.common.enums.ReceiptType

sealed interface SaveNewTransactionResult {
    data object Success: SaveNewTransactionResult
}

data class NewTransaction(
    val receiptType: ReceiptType,
    val accountId: Long,
    val categoryId: Long,
    val date: Instant,
    val actorId: Long,
    val amount: Long,
)

interface SaveNewTransactionFeatureCase {

    operator fun invoke(transaction: NewTransaction) : SaveNewTransactionResult
}
