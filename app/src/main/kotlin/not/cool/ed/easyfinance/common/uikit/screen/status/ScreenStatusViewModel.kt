package not.cool.ed.easyfinance.common.uikit.screen.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import not.cool.ed.easyfinance.common.uikit.atom.spacer.Spacer

abstract class ScreenStatusViewModel : ViewModel() {

    private val _status: MutableStateFlow<ScreenStatus> = MutableStateFlow(ScreenStatus.Loading)
    private val status: StateFlow<ScreenStatus> = _status

    protected fun showLoading() = _status.update { ScreenStatus.Loading }
    protected fun showContent() = _status.update { ScreenStatus.Content }
    protected fun showError(message: String) = _status.update { ScreenStatus.Error(message) }
    protected fun showError(cause: Throwable) = showError(cause.message
            ?: cause.cause?.message
            ?: "${cause::class.simpleName} Сообщение отсутствует")

    @Composable
    fun OnContent(content: @Composable () -> Unit) {
        val status: ScreenStatus by status.collectAsState()
        when (val s = status) {
            ScreenStatus.Content -> content()
            is ScreenStatus.Error -> s.OnError()
            is ScreenStatus.Loading -> s.OnLoading()
        }
    }

    @Composable
    protected open fun ScreenStatus.Error.OnError() {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "\uD83E\uDD15", fontSize = 28.sp)
            Spacer(16.dp)
            Text(text = "Ошибка", fontSize = 22.sp)
            Spacer(8.dp)
            Text(text = message)
        }
    }

    @Composable
    protected open fun ScreenStatus.Loading.OnLoading() {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
        }
    }

    protected sealed interface ScreenStatus {

        object Loading : ScreenStatus

        object Content : ScreenStatus

        data class Error(val message: String) : ScreenStatus

    }
}
