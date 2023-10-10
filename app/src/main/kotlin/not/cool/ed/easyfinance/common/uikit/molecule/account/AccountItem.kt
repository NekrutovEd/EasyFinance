package not.cool.ed.easyfinance.common.uikit.molecule.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import not.cool.ed.easyfinance.common.extend.screen.withEllipsisIf

@Composable
fun AccountItem(
    title: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ElevatedFilterChip(
        selected = isSelected,
        onClick = onClick,
        modifier = modifier,
        label = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title.withEllipsisIf(8), fontSize = 12.sp, maxLines = 1)
            }
        })
}
