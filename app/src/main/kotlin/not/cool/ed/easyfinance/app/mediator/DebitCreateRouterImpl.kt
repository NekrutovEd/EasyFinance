package not.cool.ed.easyfinance.app.mediator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.common.navigation.api.Navigator
import not.cool.ed.easyfinance.feature.screen.account.create.navigation.AccountCreateDirection
import not.cool.ed.easyfinance.feature.screen.category.create.navigation.CategoryCreateDirection
import not.cool.ed.easyfinance.feature.screen.debit.create.navigation.DebitCreateRouter
import javax.inject.Inject

class DebitCreateRouterImpl @Inject constructor(private val navigator: Navigator) : DebitCreateRouter {

    override fun back() = navigator.navigateUp()

    override fun toCreatingCategory() {
        navigator.navigate(CategoryCreateDirection.createAction(ReceiptType.Debit))
    }

    override fun toCreatingAccount() {
        navigator.navigate(AccountCreateDirection.createAction())
    }
}

@Module
@InstallIn(ViewModelComponent::class)
interface DebitCreateRouterModule {

    @Binds
    fun bindDebitCreateRouter(impl: DebitCreateRouterImpl): DebitCreateRouter
}
