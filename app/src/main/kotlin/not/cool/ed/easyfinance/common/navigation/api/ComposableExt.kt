package not.cool.ed.easyfinance.common.navigation.api

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import not.cool.ed.easyfinance.common.navigation.api.Direction

fun NavGraphBuilder.composableTo(direction: Direction, content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit) {
    composable(direction.route, direction.arguments, direction.deepLinks, content = content)
}
