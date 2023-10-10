package not.cool.ed.easyfinance.feature.featurecase.account.impl

import not.cool.ed.easyfinance.feature.featurecase.account.api.GetAccountsFeatureCase
import not.cool.ed.easyfinance.feature.screen.debit.create.Account
import javax.inject.Inject

class GetAccountsFeatureCaseImpl @Inject constructor(

) : GetAccountsFeatureCase {

    private val accounts = listOf("Счет1", "Счет2").map(::Account)

    override suspend fun invoke(): List<Account> = accounts
}
