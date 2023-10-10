package not.cool.ed.easyfinance.feature.data.receipt.api

interface ReceiptRepository {

    fun get(receiptId: ReceiptId): Receipt

    fun list(): List<Receipt>

    fun save(receipt: Receipt)

    fun remove(receiptId: ReceiptId)
}
