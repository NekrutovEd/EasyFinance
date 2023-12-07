package not.cool.ed.easyfinance.feature.data.transaction.impl.local.db

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.common.mapping.Mapper
import not.cool.ed.easyfinance.feature.data.transaction.api.NewTransaction
import not.cool.ed.easyfinance.feature.data.transaction.api.Transaction
import javax.inject.Inject

class TransactionMapper @Inject constructor(
    private val adapter: JsonAdapter<ReceiptType>
) {

    val entityToDomain by lazy {
        Mapper<TransactionEntity, Transaction> {
            Transaction(
                id = it.id,
                type = adapter.fromJson(it.type)
                    ?: error("TransactionEntity.type (${it.type}) as NULL after convert from json"),
                accountId = it.accountId,
                categoryId = it.categoryId,
                date = it.date,
                actorId = it.actorId,
                amount = it.amount,
            )
        }
    }

    val domainToEntity by lazy {
        Mapper<Transaction, TransactionEntity> {
            TransactionEntity(
                id = it.id,
                type = adapter.toJson(it.type),
                accountId = it.accountId,
                categoryId = it.categoryId,
                date = it.date,
                actorId = it.actorId,
                amount = it.amount,
            )
        }
    }

    val newToEntity by lazy {
        Mapper<NewTransaction, TransactionEntity> {
            TransactionEntity(
                id = 0,
                type = adapter.toJson(it.type),
                accountId = it.accountId,
                categoryId = it.categoryId,
                date = it.date,
                actorId = it.actorId,
                amount = it.amount,
            )
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal class TransactionMapperModule {

    @Provides
    fun bindTransactionMapper(adapter: JsonAdapter<ReceiptType>): TransactionMapper = TransactionMapper(adapter)

    @Provides
    fun provideJsonAdapterForSetReceiptType(moshi: Moshi): JsonAdapter<ReceiptType> = moshi.adapter()
}
