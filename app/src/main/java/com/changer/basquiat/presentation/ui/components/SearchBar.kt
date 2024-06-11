package com.changer.basquiat.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Cinza
import com.changer.basquiat.presentation.ui.theme.Preto

@Composable
fun InputSearch(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onValueChange: (String) -> Unit,
    searchExtended: Boolean,
    onSearchExtendedChange: (Boolean) -> Unit
) {
    AnimatedVisibility(visible = !searchExtended) {
        IconButton(
            onClick = { onSearchExtendedChange.invoke(true) },
            content = {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = Color.Transparent,
                            shape = CircleShape
                        ),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Icone de lupa de pesquisa",
                    tint = Preto
                )
            }
        )
    }

    AnimatedVisibility(visible = searchExtended) {
        OutlinedTextField(
            shape = RoundedCornerShape(80.dp),
            colors = TextFieldDefaults.colors(
                cursorColor = Azul,
                focusedTrailingIconColor = Azul,
                focusedIndicatorColor = Azul,
                focusedContainerColor = Branco,
                focusedLabelColor = Cinza,
                focusedPlaceholderColor = Cinza,
                unfocusedTrailingIconColor = Cinza,
                unfocusedIndicatorColor = Cinza,
                unfocusedContainerColor = Branco,
            ),
            value = searchQuery,
            onValueChange = {
                onValueChange(it)
            },
            label = {
                Text(
                    text = "Pesquisar",
                    fontSize = 18.sp,
                    color = Cinza
                )
            },
            placeholder = {
                Text(
                    text = "Ex: arquivo x",
                    fontSize = 18.sp,
                    color = Cinza
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { onSearchExtendedChange.invoke(false) },
                    content = {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Icone de lupa de pesquisa",
                        )
                    }
                )
            },
            textStyle = TextStyle(color = Preto, fontWeight = FontWeight.Bold),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewInputSearch() {
    InputSearch(
        searchQuery = "",
        onValueChange = {},
        searchExtended = false,
        onSearchExtendedChange = {})
}