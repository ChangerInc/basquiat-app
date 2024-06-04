package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    BasquiatTheme {
        TopAppBarLoginCadastro(titulo = "Teste", navigateToPage = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarLoginCadastro(
    modifier: Modifier = Modifier,
    titulo: String,
    navigateToPage: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Branco,
            titleContentColor = Preto,
        ),
        title = {
            Text(
                titulo,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateToPage) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}
