package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ProfileImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
