package com.changer.basquiat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.changer.basquiat.R
import com.changer.basquiat.ui.theme.Azul
import com.changer.basquiat.ui.theme.BasquiatTheme
import com.changer.basquiat.ui.theme.Branco
import com.changer.basquiat.ui.theme.CinzaClaro
import com.changer.basquiat.ui.theme.Preto

@Preview(showBackground = true)
@Composable
fun InputPasswordPreview() {
    var senha by remember {
        mutableStateOf("")
    }
    BasquiatTheme {
        InputPassword({
            senha
        }) {
            senha = it
        }
    }
}

@Composable
fun InputPassword(
    senha: () -> String,
    modifier: Modifier = Modifier,
    setSenha: (String) -> Unit,
) {
    var senhaVisivel: Boolean by remember { mutableStateOf(false) }
    val icon:ImageVector =
        if (senhaVisivel) {
            ImageVector.vectorResource(id = R.drawable.outline_visibility_off_24)
        }
        else {
            ImageVector.vectorResource(id = R.drawable.outline_visibility_24)
        }

    OutlinedTextField(
        shape = OutlinedTextFieldDefaults.shape,
        colors = TextFieldDefaults.colors(
            cursorColor = Azul,
            focusedLeadingIconColor = Azul,
            focusedTrailingIconColor = Azul,
            focusedIndicatorColor = Azul,
            focusedContainerColor = Branco,
            unfocusedLeadingIconColor = CinzaClaro,
            unfocusedTrailingIconColor = CinzaClaro,
            unfocusedIndicatorColor = CinzaClaro,
            unfocusedContainerColor = Branco,
        ),
        value = senha(),
        onValueChange = {
            setSenha(it)
        },
        label = {
            Text(
                text = "Senha",
                fontSize = 18.sp,
                color = CinzaClaro
            )
        },
        placeholder = {
            Text(
                text = "******",
                fontSize = 18.sp,
                color = CinzaClaro
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.outline_password_24),
                        contentDescription = "Icone da senha")
                }
            )
        },
        trailingIcon = {
                       IconButton(
                           onClick = { senhaVisivel = !senhaVisivel },
                           content = {
                               Icon(
                                   imageVector = icon,
                                   contentDescription = "Icone de visibilidade da senha")
                           }
                       )
        },
        visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        textStyle = TextStyle(color = Preto, fontWeight = FontWeight.Bold),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent
            )
    )
}