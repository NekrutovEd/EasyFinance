package not.cool.ed.easyfinance.feature.screen.debit.create.selector

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import not.cool.ed.easyfinance.common.uikit.molecule.category.AddCategoryItem
import not.cool.ed.easyfinance.common.uikit.molecule.category.CategoryItem
import not.cool.ed.easyfinance.feature.featurecase.category.api.model.DebitCategory

@Composable
fun CategorySelector(categories: List<DebitCategory>, onSelect: (DebitCategory) -> Unit, onAddClick: () -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category.title, category.emoji) {
                onSelect(category)
            }
        }
        item {
            AddCategoryItem {
                onAddClick()
            }
        }
    }
}
