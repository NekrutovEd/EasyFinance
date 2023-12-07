package not.cool.ed.easyfinance.feature.data.transaction.impl.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.app.database.AppDatabase

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction WHERE id = :transactionId")
    fun getById(transactionId: Long): TransactionEntity?

    @Query("SELECT * FROM transaction WHERE title = :title LIMIT 1")
    fun getByTitle(title: String): TransactionEntity?

    @Query("SELECT * FROM transaction")
    fun list(): List<TransactionEntity>

    @Insert
    fun insert(transaction: TransactionEntity): Long

    @Update
    fun update(transaction: TransactionEntity)

    @Delete
    fun delete(transaction: TransactionEntity)
}

@Module
@InstallIn(ViewModelComponent::class)
internal class TransactionDaoModule {

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionDao = database.transactionDao()
}
