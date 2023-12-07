package not.cool.ed.easyfinance.feature.data.transaction.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.withContext
import not.cool.ed.easyfinance.common.di.context.DispatcherIO
import not.cool.ed.easyfinance.feature.data.category.api.CategoryRepository
import not.cool.ed.easyfinance.feature.data.category.impl.CategoryRepositoryImpl
import not.cool.ed.easyfinance.feature.data.transaction.api.Transaction
import not.cool.ed.easyfinance.feature.data.transaction.api.TransactionRepository
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.LocalTransactionDataSource
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class TransactionRepositoryImpl @Inject constructor(
    private val localCategoryDataSource: LocalTransactionDataSource,
    @DispatcherIO private val context: CoroutineContext,
) : TransactionRepository {

    override suspend fun getById(transactionId: Long): Transaction? = withContext(context) {
        localCategoryDataSource.getById(transactionId)
    }

    override suspend fun getByTitle(title: String): Transaction? = withContext(context) {
        localCategoryDataSource.getByTitle(title)
    }

    override suspend fun list(): List<Transaction> = withContext(context) {
        localCategoryDataSource.list()
    }

    override suspend fun save(newTransaction: NewTransaction): Transaction? = withContext(context) {
        localCategoryDataSource.save(newTransaction)
    }

    override suspend fun save(transaction: Transaction) = withContext(context) {
        localCategoryDataSource.update(transaction)
    }

    override suspend fun remove(transaction: Transaction) = withContext(context) {
        localCategoryDataSource.remove(transaction)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal interface CategoryRepositoryModule {

    @Binds
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}
