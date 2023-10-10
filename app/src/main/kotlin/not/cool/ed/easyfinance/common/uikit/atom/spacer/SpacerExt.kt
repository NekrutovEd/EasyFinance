package not.cool.ed.easyfinance.common.uikit.atom.spacer

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Spacer(dp: Dp) = androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(dp))
