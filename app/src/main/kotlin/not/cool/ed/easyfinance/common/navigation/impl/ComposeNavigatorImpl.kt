package not.cool.ed.easyfinance.common.navigation.impl

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import not.cool.ed.easyfinance.common.navigation.api.NavigationAction
import not.cool.ed.easyfinance.common.navigation.api.Navigator
import javax.inject.Inject

@ActivityRetainedScoped
class ComposeNavigatorImpl @Inject constructor() : Navigator {

    private val _navActions = Channel<Navigator.Direction>()
    override val navActions: ReceiveChannel<Navigator.Direction> = _navActions

    override fun navigate(navAction: NavigationAction) {
        _navActions.trySend(Navigator.Direction.Forward(navAction))
    }

    override fun navigateUp() {
        _navActions.trySend(Navigator.Direction.Back)
    }
}
