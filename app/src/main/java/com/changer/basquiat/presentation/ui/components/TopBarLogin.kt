package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.InsertPhoto
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.changer.basquiat.R
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto

@Preview(showBackground = true)
@Composable
fun TopBarLoginPreview() {
    BasquiatTheme {
        TopBarLogin(
            titulo = "Teste",
            notification = 1,
            url = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fblogdataverna.com.br%2Fblog%2Fjujutsu-kaisen-gojo-vs-sukuna%2F&psig=AOvVaw2jaiE6PipylRsp6bFaY2v3&ust=1717703226415000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCNiz97idxYYDFQAAAAAdAAAAABAE",
            openDialogInvites = {},
            openDialogChangePhoto = {},
            logout = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLogin(
    modifier: Modifier = Modifier,
    titulo: String,
    notification: Int?,
    url: String,
    openDialogInvites: (Boolean) -> Unit,
    openDialogChangePhoto: (Boolean) -> Unit,
    logout: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Azul,
            titleContentColor = Preto
        ),
        navigationIcon = {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.c_white),
                contentDescription = "Logo"
            )
        },
        title = {
            Text(
                titulo,
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = Branco,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.fillMaxWidth()
            )
        },
        actions = {
            NotificationIcon(
                onClick = { openDialogInvites(true) },
                notification = notification ?: 0
            )
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Branco)
                    .clickable { expanded = true },
                painter = rememberAsyncImagePainter(model = url),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                contentDescription = "Foto de perfil",
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Branco)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Alterar foto",
                            fontSize = 20.sp,
                            color = Preto
                        )
                    },
                    onClick = {
                        openDialogChangePhoto(true)
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.InsertPhoto,
                            contentDescription = "Alterar foto de perfil",
                            tint = Preto
                        )
                    })
                HorizontalDivider()
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Sair",
                            fontSize = 20.sp,
                            color = Preto
                        )
                    },
                    onClick = { logout() },
                    leadingIcon = {
                        Icon(
                            Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = "Icone de logout",
                            tint = Color.Red
                        )
                    })
            }
        }
    )
}
