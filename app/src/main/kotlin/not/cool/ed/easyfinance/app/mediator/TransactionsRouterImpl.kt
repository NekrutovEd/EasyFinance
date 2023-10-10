package not.cool.ed.easyfinance.app.mediator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.common.navigation.api.Navigator
import not.cool.ed.easyfinance.feature.screen.transactions.navigation.TransactionsRouter
import javax.inject.Inject

class TransactionsRouterImpl @Inject constructor(private val navigator: Navigator) : TransactionsRouter {

    override fun back() = navigator.navigateUp()
}

@Module
@InstallIn(ViewModelComponent::class)
interface TransactionsRouterModule {

    @Binds
    fun bindTransactionsRouter(impl: TransactionsRouterImpl): TransactionsRouter
}
