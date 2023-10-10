package not.cool.ed.easyfinance.feature.screen.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import not.cool.ed.easyfinance.feature.screen.main.navigation.MainRouter
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val router: MainRouter
) : ViewModel(), MainRouter by router {

    fun addDebit() {
        router.toCreateDebit()
    }
}
