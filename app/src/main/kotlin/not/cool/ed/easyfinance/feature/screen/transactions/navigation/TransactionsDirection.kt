package not.cool.ed.easyfinance.feature.screen.transactions.navigation

import not.cool.ed.easyfinance.common.navigation.api.Direction

object TransactionsDirection : Direction("transactions") {

    fun createAction() = createNavAction()
}
