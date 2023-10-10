package not.cool.ed.easyfinance.common.navigation.api

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions

class NavigationAction private constructor(
    val destination: String,
    val navOptions: NavOptions? = null,
) {
    constructor(
        destination: String,
        builder: (NavOptionsBuilder.() -> Unit)? = null,
    ) : this(destination, builder?.let(::navOptions))
}
