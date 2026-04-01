package com.example.pruebadesarrollador.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ImageInfoItem(
    title: String,
    imageUrl: String?
) {

    if (imageUrl.isNullOrEmpty()) return

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(4.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        )
    }
}