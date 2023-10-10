package not.cool.ed.easyfinance.app.mediator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.common.navigation.api.Navigator
import not.cool.ed.easyfinance.feature.screen.category.create.navigation.CategoryCreateRouter
import javax.inject.Inject

class CategoryCreateRouterImpl @Inject constructor(private val navigator: Navigator) : CategoryCreateRouter {

    override fun back() = navigator.navigateUp()
}

@Module
@InstallIn(ViewModelComponent::class)
interface CategoryCreateRouterModule {

    @Binds
    fun bindCategoryCreateRouter(impl: CategoryCreateRouterImpl): CategoryCreateRouter
}
