package com.example.pruebadesarrollador.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun CountryItem(
    imageUrl: String,
    commonName: String,
    officialName: String,
    capital: String,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.padding(12.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier.weight(0.2f)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = commonName,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 40.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(0.8f)) {
                    Text(commonName, style = MaterialTheme.typography.titleMedium)
                    Text(officialName, color = Color.Gray)
                    Text(capital, color = Color.Gray.copy(alpha = 0.6f))
                }
            }

            IconToggleButton(
                checked = isFavorite,
                onCheckedChange = { onFavoriteClick() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Bookmark else Icons.Outlined.Bookmark,
                    contentDescription = null,
                    tint = if (isFavorite) Color(0xFF2196F3) else Color.Gray,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryItemPreview() {
    MaterialTheme {
        CountryItem(
            imageUrl = "https://flagcdn.com/br.svg",
            commonName = "Brazil",
            officialName = "Federative Republic of Brazil",
            capital = "Brasília",
            isFavorite = true,
            onFavoriteClick = {}
        )
    }
}