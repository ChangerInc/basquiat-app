package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Cinza

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

    NavigationBar(
        containerColor = Azul
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = Cinza,
                    selectedIconColor = Branco,
                    selectedTextColor = Branco,
                    unselectedIconColor = Branco,
                    unselectedTextColor = Branco,
                    disabledIconColor = Branco,
                    disabledTextColor = Branco
                ),
                selected = selectedItem == index,
                onClick = {
                    item.navigateToPage()
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "Icone tela conversão",
                        tint = Branco
                    )
                },
                label = {
                    Text(
                        color = Branco,
                        text = item.label
                    )
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
