package not.cool.ed.easyfinance.common.navigation.api

import kotlinx.coroutines.channels.ReceiveChannel

interface Navigator {

    val navActions: ReceiveChannel<Direction>

    fun navigate(navAction: NavigationAction)

    fun navigateUp()

    sealed interface Direction {
        data class Forward(val action: NavigationAction) : Direction
        object Back : Direction
    }
}
