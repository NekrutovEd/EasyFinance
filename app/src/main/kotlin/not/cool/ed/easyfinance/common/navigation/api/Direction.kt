package not.cool.ed.easyfinance.common.navigation.api

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavOptionsBuilder

abstract class Direction(protected val target: String) {
    open val route: String = target
    open val arguments: List<NamedNavArgument> get() = emptyList()
    open val deepLinks: List<NavDeepLink> get() = emptyList()

    protected fun createNavAction(destination: String = target, builder: (NavOptionsBuilder.() -> Unit)? = null) =
        NavigationAction(destination = destination, builder = builder)
}
