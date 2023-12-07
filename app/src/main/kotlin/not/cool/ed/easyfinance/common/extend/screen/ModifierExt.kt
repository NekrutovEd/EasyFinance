@file:OptIn(ExperimentalContracts::class)

package not.cool.ed.easyfinance.common.extend.screen

import androidx.compose.ui.Modifier
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Modifier.applyIf(predicate: Boolean, block: Modifier.() -> Modifier): Modifier {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return if (predicate) block() else this
}
