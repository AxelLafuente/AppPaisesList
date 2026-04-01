
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.pruebadesarrollador.domain.model.CountryDetail
import com.example.pruebadesarrollador.presentation.CountryDetails.CountryDetailUiState
import com.example.pruebadesarrollador.presentation.CountryDetails.CountryDetailViewModel
import com.example.pruebadesarrollador.presentation.components.DriverSideItem
import com.example.pruebadesarrollador.presentation.components.ImageInfoItem
import com.example.pruebadesarrollador.presentation.components.InfoItem

@Composable
fun CountryDetailScreen(
    countryName: String,
    onBackClick: () -> Unit,
    viewModel: CountryDetailViewModel = viewModel()
) {

    val state = viewModel.uiState


    LaunchedEffect(countryName) {
        viewModel.fetchCountry(countryName)
    }

    when (state) {

        is CountryDetailUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Carregando...")
            }
        }

        is CountryDetailUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(state.message)
            }
        }

        is CountryDetailUiState.Success -> {

            val country = state.data

            var imageUrl by remember {
                mutableStateOf(country.flagUrl)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                DetailHeader(onBackClick)


                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Text(
                    text = country.name,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = country.officialName,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Column(Modifier.weight(1f)) {
                        ImageInfoItem("Coat of Arms", country.coatOfArmsUrl)
                        InfoItem("Region", country.region)
                        InfoItem("Subregion", country.subregion)
                        InfoItem("Capital", country.capital)
                        InfoItem("Area", country.area)
                    }

                    Column(Modifier.weight(1f)) {
                        InfoItem("Population", country.population)
                        InfoItem("Languages", country.languages)
                        DriverSideItem(country.driverSide)
                        InfoItem("Currencies", country.currencies)
                        InfoItem("Timezone", country.timezone)
                    }
                }
            }
        }
    }
}
private fun mockCountryDetail() = CountryDetail(
    name = "Brazil",
    officialName = "Federative Republic of Brazil",
    flagUrl = "https://flagcdn.com/br.svg",
    coatOfArmsUrl ="https://mainfacts.com/media/images/coats_of_arms/br.svg" ,
    region = "Americas",
    subregion = "South America",
    capital = "Brasília",
    area = "8,515,767 km²",
    population = "203,080,756",
    languages = "Portuguese",
    driverSide = "right",
    currencies = "Brazilian real (R$)",
    timezone = "UTC−03:00"
)



@Composable
fun DetailHeader(
    onBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {

        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        Text(
            text = "Countries",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CountryDetailScreenPreview() {

    val country = mockCountryDetail()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(country.flagUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Text(
            text = country.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = country.officialName,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Column(Modifier.weight(1f)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(country.coatOfArmsUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                InfoItem("Region", country.region)
                InfoItem("Subregion", country.subregion)
                InfoItem("Capital", country.capital)
                InfoItem("Area", country.area)
            }

            Column(Modifier.weight(1f)) {
                InfoItem("Population", country.population)
                InfoItem("Languages", country.languages)
                InfoItem("Currencies", country.currencies)
                InfoItem("Timezone", country.timezone)
            }
        }
    }
}