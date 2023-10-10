package not.cool.ed.easyfinance.app.mediator.graph

import androidx.navigation.NavGraphBuilder
import not.cool.ed.easyfinance.feature.screen.account.create.composableToAccountCreate
import not.cool.ed.easyfinance.feature.screen.category.create.composableToCategoryCreate
import not.cool.ed.easyfinance.feature.screen.debit.create.composableToDebitCreate
import not.cool.ed.easyfinance.feature.screen.main.composableToMain
import not.cool.ed.easyfinance.feature.screen.transactions.composableToTransactions

fun NavGraphBuilder.composableAll() {
    composableToMain()
    composableToTransactions()
    composableToDebitCreate()
    composableToCategoryCreate()
    composableToAccountCreate()
}
