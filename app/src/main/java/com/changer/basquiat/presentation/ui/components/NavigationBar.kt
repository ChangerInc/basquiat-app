package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.TableRows
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.presentation.ui.navigate.Screen
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Cinza

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    BasquiatTheme {
        NavigateBar(
            navController = rememberNavController(),
            screens = listOf(Screen.Home, Screen.Historic, Screen.Circles),
            selectedScreen = Screen.Historic
        )
    }
}

@Composable
fun NavigateBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    screens: List<Screen>,
    selectedScreen: Screen
) {
    var selectedItem by remember { mutableIntStateOf(screens.indexOf(selectedScreen)) }
    val items = remember {
        screens.map {screen ->
            ItemsNavigationBar(
                screen.route,
                { navController.navigate(screen.route); selectedItem = screens.indexOf(screen) },
                when(screen) {
                    Screen.Conversion -> if (screen == selectedScreen) Icons.Filled.ChangeCircle else Icons.Outlined.ChangeCircle
                    Screen.Historic -> if (screen == selectedScreen) Icons.Filled.TableRows else Icons.Outlined.TableRows
                    else -> if (screen == selectedScreen) Icons.Filled.Groups else Icons.Outlined.Groups
                }
            )
        }
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
                        contentDescription = "Icones de BottomAppBar",
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
