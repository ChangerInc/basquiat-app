package com.changer.basquiat.presentation.ui.conversion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto

@Composable
fun DialogConversion(
    options: List<String>,
    onDismissRequest: (Boolean) -> Unit,
    onOptionSelected: (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf(options.firstOrNull() ?: "") }

    Dialog(
        onDismissRequest = { onDismissRequest(true) }
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight(0.4f)
                .background(Branco, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .selectableGroup(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                color = Preto,
                text = "Selecione uma opção",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(options) { option ->
                    SelectItem(
                        onOptionSelected = { selected ->
                            onOptionSelected(selected)
                            selectedOption = selected
                        },
                        selectedOption = selectedOption,
                        option = option
                    )
                }
            }
        }
    }
}

@Composable
fun SelectItem(onOptionSelected: (String) -> Unit, selectedOption: String, option: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = {
                onOptionSelected(option)
            },
            colors = RadioButtonColors(
                selectedColor = Azul,
                unselectedColor = Preto,
                disabledSelectedColor = Preto,
                disabledUnselectedColor = Preto
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            color = Preto,
            text = option
        )
    }
}

@Preview
@Composable
fun DialogConversionPreview() {
    DialogConversion(
        options = listOf("Option 1", "Option 2", "Option 3"),
        onDismissRequest = {},
        onOptionSelected = {}
    )
}