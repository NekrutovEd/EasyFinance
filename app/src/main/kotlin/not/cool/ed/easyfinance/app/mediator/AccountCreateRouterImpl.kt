package not.cool.ed.easyfinance.app.mediator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.common.navigation.api.Navigator
import not.cool.ed.easyfinance.feature.screen.account.create.navigation.AccountCreateRouter
import javax.inject.Inject

class AccountCreateRouterImpl @Inject constructor(private val navigator: Navigator) : AccountCreateRouter {

    override fun back() = navigator.navigateUp()
}

@Module
@InstallIn(ViewModelComponent::class)
interface AccountCreateRouterModule {

    @Binds
    fun bindAccountCreateRouter(impl: AccountCreateRouterImpl): AccountCreateRouter
}
