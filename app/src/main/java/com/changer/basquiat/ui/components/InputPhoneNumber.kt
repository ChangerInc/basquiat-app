package com.changer.basquiat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.changer.basquiat.ui.theme.Azul
import com.changer.basquiat.ui.theme.BasquiatTheme
import com.changer.basquiat.ui.theme.Branco
import com.changer.basquiat.ui.theme.CinzaClaro

@Preview(showBackground = true)
@Composable
fun InputPhoneNumberPreview() {
    BasquiatTheme {
        InputPhoneNumber()
    }
}

@Composable
fun InputPhoneNumber(
    modifier: Modifier = Modifier
) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }

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
        value = phoneNumber,
        onValueChange = {
            if (it.length < 12) {
                phoneNumber = it
            }
        },
        label = {
            Text(
                text = "Telefone",
                fontSize = 18.sp,
                color = CinzaClaro
            )
        },
        placeholder = {
            Text(
                text = "(XX) XXXXX-XXXX",
                fontSize = 18.sp,
                color = CinzaClaro
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "Icone de smartphone")
                }
            )
        },
        visualTransformation = PhoneVisualTransformation(),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent
            )
    )
}

class PhoneVisualTransformation : VisualTransformation {
    //(XX) XXXXX-XXXX
    override fun filter(text: AnnotatedString): TransformedText {
        val phoneMask = text.text.mapIndexed { index, c ->
            when(index) {
                0 -> "($c"
                1 -> "$c) "
                6 -> "$c-"
                    else -> c
            }
        }.joinToString(separator = "")

        return TransformedText(
            AnnotatedString(phoneMask),
            offsetMapping = PhoneOffsetMapping
        )
    }

}

object PhoneOffsetMapping : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return when {
            offset > 6 -> offset + 4
            offset > 1 -> offset + 3
            offset > 0 -> offset + 1
            else -> offset
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        return when {
            offset > 6 -> offset - 4
            offset > 1 -> offset - 3
            offset > 0 -> offset - 1
            else -> offset
        }
    }

}
