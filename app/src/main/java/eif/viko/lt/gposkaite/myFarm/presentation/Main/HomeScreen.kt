package eif.viko.lt.gposkaite.myFarm.presentation.Main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eif.viko.lt.gposkaite.R
import eif.viko.lt.gposkaite.myFarm.data.Adds.Advertisement

@Composable
fun HomeScreen() {
    val advertisements = listOf(
        Advertisement(
            title = stringResource(id = R.string.title_1),
            description = stringResource(id = R.string.description_1),
            image = painterResource(id = R.drawable.vistos),
            rating = 5,
            location = "Jasinskio g. 15"
        ),
        Advertisement(
            title = stringResource(id = R.string.title_2),
            description = stringResource(id = R.string.description_2),
            image = painterResource(id = R.drawable.antys),
            rating = 4,
            location = "Vilniaus g. 20A"
        ),
        Advertisement(
            title = stringResource(id = R.string.title_3),
            description = stringResource(id = R.string.description_3),
            image = painterResource(id = R.drawable.ozkos),
            rating = 3,
            location = "Kolegijos g. 6"
        ),
        Advertisement(
            title = stringResource(id = R.string.title_4),
            description = stringResource(id = R.string.description_4),
            image = painterResource(id = R.drawable.avys),
            rating = 5,
            location = "Fermos g. 102"
        ),
        Advertisement(
            title = stringResource(id = R.string.title_5),
            description = stringResource(id = R.string.description_5),
            image = painterResource(id = R.drawable.jautis),
            rating = 4,
            location = "Namų g. 77"
        )
    )
    Scaffold(
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(Color(0xFFFFEBF2)),
            ) {
                items(advertisements) { advertisement ->
                    AdvertisementItem(advertisement = advertisement)
                }
            }
        }
    )
}

@Composable
fun AdvertisementItem(advertisement: Advertisement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(Color(0x5BFF0052)),
        border = BorderStroke(3.dp, color = Color(0xD8750026))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = advertisement.title,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = advertisement.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = advertisement.description,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Default.Star, contentDescription = "Rating", tint = Color.White)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${advertisement.rating} stars", color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Default.Place, contentDescription = "Location", tint = Color.White)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = advertisement.location, color = Color.White)
            }
        }
    }
}