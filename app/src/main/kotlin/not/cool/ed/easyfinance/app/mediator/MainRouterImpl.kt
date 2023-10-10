package not.cool.ed.easyfinance.app.mediator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.common.navigation.api.Navigator
import not.cool.ed.easyfinance.feature.screen.debit.create.navigation.DebitCreateDirection
import not.cool.ed.easyfinance.feature.screen.main.navigation.MainRouter
import not.cool.ed.easyfinance.feature.screen.transactions.navigation.TransactionsDirection
import javax.inject.Inject

class MainRouterImpl @Inject constructor(private val navigator: Navigator) : MainRouter {

    override fun toTransactions() = navigator.navigate(TransactionsDirection.createAction())

    override fun toCreateDebit() = navigator.navigate(DebitCreateDirection.createAction())
}

@Module
@InstallIn(ViewModelComponent::class)
interface MainRouterModule {

    @Binds
    fun bindMainRouter(impl: MainRouterImpl): MainRouter
}
