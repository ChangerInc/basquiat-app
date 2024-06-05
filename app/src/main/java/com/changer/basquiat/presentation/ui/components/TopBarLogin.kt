package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto

@Preview(showBackground = true)
@Composable
fun TopBarLoginPreview() {
    BasquiatTheme {
        TopBarLogin(titulo = "Teste", url = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fblogdataverna.com.br%2Fblog%2Fjujutsu-kaisen-gojo-vs-sukuna%2F&psig=AOvVaw2jaiE6PipylRsp6bFaY2v3&ust=1717703226415000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCNiz97idxYYDFQAAAAAdAAAAABAE")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLogin(
    modifier: Modifier = Modifier,
    titulo: String,
    url: String
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Azul,
            titleContentColor = Preto
        ),
        title = {
            Text(
                titulo,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                color = Branco,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.fillMaxWidth()
            )
        },
        actions = {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(model = url),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                contentDescription = "Profile Picture",
            )
        }
    )
}
