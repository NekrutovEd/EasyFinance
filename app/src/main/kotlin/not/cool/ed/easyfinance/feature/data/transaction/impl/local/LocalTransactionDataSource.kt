package not.cool.ed.easyfinance.feature.data.transaction.impl.local

import not.cool.ed.easyfinance.feature.data.transaction.api.NewTransaction
import not.cool.ed.easyfinance.feature.data.transaction.api.Transaction

interface LocalTransactionDataSource {

    suspend fun getById(transactionId: Long): Transaction?

    suspend fun getByTitle(title: String): Transaction?

    suspend fun list(): List<Transaction>

    suspend fun save(transaction: NewTransaction): Transaction?

    suspend fun update(transaction: Transaction)

    suspend fun remove(transaction: Transaction)
}
