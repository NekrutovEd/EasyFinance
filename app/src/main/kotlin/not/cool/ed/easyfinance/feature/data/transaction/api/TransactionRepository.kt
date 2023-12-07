package not.cool.ed.easyfinance.feature.data.transaction.api

interface TransactionRepository {

    suspend fun getById(transactionId: Long): Transaction?

    suspend fun getByTitle(title: String): Transaction?

    suspend fun list(): List<Transaction>

    suspend fun save(newTransaction: NewTransaction): Transaction?

    suspend fun save(transaction: Transaction)

    suspend fun remove(transaction: Transaction)
}
