package not.cool.ed.easyfinance.common.uikit.molecule.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AddAccountItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedFilterChip(
        selected = false,
        onClick = onClick,
        modifier = modifier,
        label = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Rounded.Add, "Create account")
            }
        })
}
