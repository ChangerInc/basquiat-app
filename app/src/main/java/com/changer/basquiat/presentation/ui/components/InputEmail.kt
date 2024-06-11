package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    val email by remember {
        mutableStateOf("")
    }
    BasquiatTheme {
        InputEmail(
            email = { email },
            setEmail = { },
            inputColor = { Azul },
            focusRequester = { FocusRequester() },
            onImeAction = { }
        )
    }
}

@Composable
fun InputEmail(
    email: () -> String,
    modifier: Modifier = Modifier,
    setEmail: (String) -> Unit,
    inputColor: () -> Color,
    focusRequester: () -> FocusRequester,
    onImeAction: () -> Unit
) {

    OutlinedTextField(
        shape = OutlinedTextFieldDefaults.shape,
        colors = TextFieldDefaults.colors(
            cursorColor = Azul,
            focusedLeadingIconColor = Azul,
            focusedIndicatorColor = inputColor(),
            focusedContainerColor = Branco,
            unfocusedLeadingIconColor = CinzaClaro,
            unfocusedIndicatorColor = inputColor(),
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {
            focusRequester().requestFocus()
            onImeAction()
        }),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent
            )
            .focusRequester(focusRequester())
    )
}