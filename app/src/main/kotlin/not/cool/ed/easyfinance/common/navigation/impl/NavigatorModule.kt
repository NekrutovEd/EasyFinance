package not.cool.ed.easyfinance.common.navigation.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import not.cool.ed.easyfinance.common.navigation.api.Navigator

@Module
@InstallIn(ActivityRetainedComponent::class)
interface NavigatorModule {

    @Binds
    @ActivityRetainedScoped
    fun bindNavigator(impl: ComposeNavigatorImpl): Navigator
}
