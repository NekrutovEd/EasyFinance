package not.cool.ed.easyfinance.feature.featurecase.transaction.impl

import not.cool.ed.easyfinance.feature.featurecase.transaction.api.NewTransaction
import not.cool.ed.easyfinance.feature.featurecase.transaction.api.SaveNewTransactionFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.transaction.api.SaveNewTransactionResult
import javax.inject.Inject

internal class SaveTransactionFeatureCaseImpl @Inject constructor(

) : SaveNewTransactionFeatureCase {

    override fun invoke(transaction: NewTransaction) : SaveNewTransactionResult {
        return SaveNewTransactionResult.Success
    }
}
