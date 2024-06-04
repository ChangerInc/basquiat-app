package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.changer.basquiat.R
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    BasquiatTheme {
        NavigateBar(
            navigateToHistorico = {},
            navigateToConversao = {},
            navigateToCirculos = {},
            selectedScreen = 1
        )
    }
}

@Composable
fun NavigateBar(
    modifier: Modifier = Modifier,
    navigateToHistorico: () -> Unit,
    navigateToConversao: () -> Unit,
    navigateToCirculos: () -> Unit,
    selectedScreen: Int
) {
    var selectedItem by remember { mutableIntStateOf(selectedScreen) }
    val items = remember {
        listOf(
            ItemsNavigationBar(
                "Conversor",
                { navigateToConversao(); selectedItem = 0 },
                Icons.Filled.ChangeCircle
            ),
            ItemsNavigationBar(
                "Histórico",
                { navigateToHistorico(); selectedItem = 1 },
                Icons.Filled.TableRows
            ),
            ItemsNavigationBar(
                "Círculos",
                { navigateToCirculos(); selectedItem = 2 },
                Icons.Filled.Groups
            )
        )
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { item.navigateToPage() },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "Icone tela conversão"
                    )
                },
                label = {
                    Text(item.label)
                },
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

data class ItemsNavigationBar(
    val label: String,
    val navigateToPage: () -> Unit,
    val icon: ImageVector
)
