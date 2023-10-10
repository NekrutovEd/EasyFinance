package not.cool.ed.easyfinance.common.extend.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.asSuccess(): Flow<Result<T>> = map(Result.Companion::success)
