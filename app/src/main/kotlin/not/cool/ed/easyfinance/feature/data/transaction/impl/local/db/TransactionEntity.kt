package not.cool.ed.easyfinance.feature.data.transaction.impl.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "transaction")
class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: String,
    val accountId: Long,
    val categoryId: Long,
    val date: Instant,
    val actorId: Long,
    val amount: Long,
)
