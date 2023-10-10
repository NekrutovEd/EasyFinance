package not.cool.ed.easyfinance.common.uikit.molecule.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import not.cool.ed.easyfinance.common.extend.screen.withEllipsisIf

@Composable
fun CategoryItem(
    title: String,
    emoji: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        ElevatedCard(
            onClick = onClick,
            modifier = Modifier
                .aspectRatio(1f)
                .padding(6.dp),
            colors = CardDefaults.elevatedCardColors(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = emoji, fontSize = 24.sp, maxLines = 1)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = title.withEllipsisIf(8), fontSize = 12.sp, maxLines = 1)
            }
        }
    }
}
