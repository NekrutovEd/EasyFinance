package not.cool.ed.easyfinance.common.di.context

import kotlin.coroutines.CoroutineContext

interface Dispatchers {
    val main: CoroutineContext
    val default: CoroutineContext
    val unconfined: CoroutineContext
    val io: CoroutineContext
    val log: CoroutineContext
}
