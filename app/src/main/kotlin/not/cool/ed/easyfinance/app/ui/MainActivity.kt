package not.cool.ed.easyfinance.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.consumeEach
import not.cool.ed.easyfinance.app.mediator.graph.composableAll
import not.cool.ed.easyfinance.common.navigation.api.Navigator
import not.cool.ed.easyfinance.feature.screen.main.navigation.MainDirection
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            LaunchedEffect(navController) {
                navigator.navActions.consumeEach { navigatorState ->
                    when (val state = navigatorState) {
                        is Navigator.Direction.Forward -> navController.navigate(state.action.destination, state.action.navOptions)
                        is Navigator.Direction.Back -> navController.navigateUp()
                    }
                }
            }

//            EasyFinanceTheme(darkTheme = false) {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = MainDirection.route, builder = NavGraphBuilder::composableAll)
                }
            }
        }
    }
}
