package com.changer.basquiat.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.changer.basquiat.ui.theme.BasquiatTheme
import com.changer.basquiat.ui.theme.Branco
import com.changer.basquiat.ui.theme.Preto

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    BasquiatTheme {
        TopAppBarLoginCadastro(titulo = "Teste", navigateToPage = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarLoginCadastro(titulo:String, navigateToPage: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Branco,
                titleContentColor = Preto,
            ),
            title = {
                Text(
                    titulo,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                },
            navigationIcon = {
                IconButton(onClick = navigateToPage) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            },
            scrollBehavior = scrollBehavior,
            )
}
