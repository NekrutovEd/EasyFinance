package not.cool.ed.easyfinance.feature.featurecase.account.api

import not.cool.ed.easyfinance.feature.screen.debit.create.Account

interface GetAccountsFeatureCase {

    suspend operator fun invoke() : List<Account>
}
