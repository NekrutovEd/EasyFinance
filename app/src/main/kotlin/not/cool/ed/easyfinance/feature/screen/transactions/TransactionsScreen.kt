package not.cool.ed.easyfinance.feature.screen.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import not.cool.ed.easyfinance.app.ui.theme.EasyFinanceTheme
import not.cool.ed.easyfinance.common.navigation.api.composableTo
import not.cool.ed.easyfinance.feature.screen.transactions.navigation.TransactionsDirection

fun NavGraphBuilder.composableToTransactions() = composableTo(TransactionsDirection) { TransactionsScreen() }

@Composable
fun TransactionsScreen(viewModel: TransactionsViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TransactionsScreen")
                },
                navigationIcon = {
                    IconButton(viewModel::back) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val transactions: List<Int> = (1..100).toList()

            LazyColumn {
                items(transactions) {
                    Text(text = it.toString())
                    Divider(
                        Modifier
                            .background(color = Color.Blue)
                            .size(1.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionsScreenPreview() {
    EasyFinanceTheme {
        TransactionsScreen()
    }
}
