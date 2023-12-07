package not.cool.ed.easyfinance.feature.data.transaction.impl.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.feature.data.transaction.api.NewTransaction
import not.cool.ed.easyfinance.feature.data.transaction.api.Transaction
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.TransactionDao
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.TransactionMapper
import javax.inject.Inject
import javax.inject.Provider

internal class LocalTransactionDataSourceImpl @Inject constructor(
    private val daoProvider: Provider<TransactionDao>,
    private val mapper: TransactionMapper,
) : LocalTransactionDataSource {

    private val dao get() = daoProvider.get()

    override suspend fun getById(categoryId: Long): Transaction? = dao.getById(categoryId)?.let(mapper.entityToDomain)

    override suspend fun getByTitle(title: String): Transaction? = dao.getByTitle(title)?.let(mapper.entityToDomain)

    override suspend fun list(): List<Transaction> = dao.list().map(mapper.entityToDomain)

    override suspend fun save(transaction: NewTransaction): Transaction? {
        val newCategoryId = dao.insert(mapper.newToEntity(transaction))
        return getById(newCategoryId)
    }

    override suspend fun update(transaction: Transaction) = dao.update(mapper.domainToEntity(transaction))

    override suspend fun remove(transaction: Transaction) = dao.delete(mapper.domainToEntity(transaction))
}

@Module
@InstallIn(ViewModelComponent::class)
internal interface LocalTransactionDataSourceModule {

    @Binds
    fun bindLocalTransactionDataSource(impl: LocalTransactionDataSourceImpl): LocalTransactionDataSource
}
