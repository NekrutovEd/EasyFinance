package not.cool.ed.easyfinance.common.uikit.atom.chip

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import not.cool.ed.easyfinance.common.uikit.atom.spacer.Spacer

@Composable
fun SwitchChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ElevatedFilterChip(
        selected = isSelected,
        onClick = onClick,
        modifier = modifier,
        label = {
            Icon(
                imageVector = if (isSelected) Icons.Rounded.Check else Icons.Rounded.Clear,
                contentDescription = "$text $isSelected"
            )
            Spacer(4.dp)
            Text(text)
        })
}
