package com.changer.basquiat.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.R
import com.changer.basquiat.R.drawable
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToLogin = {},
        navigateToRegister = {}
    )
}

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = drawable.background_home),
            contentDescription = "Imagem de fundo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 70.dp
                )
        ) {
            Image(
                painter = painterResource(id = drawable.changer),
                contentDescription = "Logo do aplicativo",
                modifier = Modifier
                    .size(width = 250.dp, height = 80.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.titulo_tela_inicial_nao_logado)
                        + " clique!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(48.dp, 64.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                shape = RoundedCornerShape(6.dp),
                onClick = navigateToRegister,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Azul,
                    contentColor = Branco
                ),
                modifier = Modifier
                    .size(width = 281.dp, height = 53.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Cadastro",
                    fontSize = 22.sp
                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                shape = RoundedCornerShape(6.dp),
                onClick = navigateToLogin,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Branco,
                    contentColor = Preto
                ),
                modifier = Modifier
                    .size(width = 281.dp, height = 53.dp)
                    .align(Alignment.CenterHorizontally)
                    .border(
                        1.dp,
                        Azul,
                        RoundedCornerShape(6.dp)
                    )
            ) {
                Text(
                    text = "Login",
                    fontSize = 22.sp
                )
            }
        }
    }
}