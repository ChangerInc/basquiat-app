package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import okhttp3.MultipartBody

@Composable
fun ChangePhoto(
    onFileSelected: (file: MultipartBody.Part) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(Branco),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BoxConversion(onFileSelected = { file -> onFileSelected(file) })
    }
}

@Preview
@Composable
private fun ChangePhotoPreview() {
    BasquiatTheme {
        ChangePhoto(
            onFileSelected = {},
            modifier = Modifier
        )
    }
}