package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.CinzaClaro
import com.changer.basquiat.presentation.ui.theme.Preto

@Preview(showBackground = true)
@Composable
fun InputEmailPreview() {
    var email by remember {
        mutableStateOf("")
    }
    BasquiatTheme {
        InputEmail({
            email
        }) {
            email = it
        }
    }
}

@Composable
fun InputEmail(
    email: () -> String,
    modifier: Modifier = Modifier,
    setEmail: (String) -> Unit,
) {

    OutlinedTextField(
        shape = OutlinedTextFieldDefaults.shape,
        colors = TextFieldDefaults.colors(
            cursorColor = Azul,
            focusedLeadingIconColor = Azul,
            focusedIndicatorColor = Azul,
            focusedContainerColor = Branco,
            unfocusedLeadingIconColor = CinzaClaro,
            unfocusedIndicatorColor = CinzaClaro,
            unfocusedContainerColor = Branco,
        ),
        value = email(),
        onValueChange = {
            setEmail(it)
        },
        label = {
            Text(
                text = "Email",
                fontSize = 18.sp,
                color = CinzaClaro
            )
        },
        placeholder = {
            Text(
                text = "email@exemplo.com",
                fontSize = 18.sp,
                color = CinzaClaro
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Icone de e-mail"
                    )
                }
            )
        },
        textStyle = TextStyle(color = Preto, fontWeight = FontWeight.Bold),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent
            )
    )
}