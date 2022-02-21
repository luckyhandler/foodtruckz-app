package de.handler.foodtruckz.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import de.handler.foodtruckz.library.data.data.Foodtruck


@Composable
@OptIn(ExperimentalCoilApi::class)
fun FoodtruckDetails(foodtruck: Foodtruck) {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Box(modifier = Modifier.size(32.dp))
        Image(
            alignment = Alignment.TopCenter,
            painter = rememberImagePainter(foodtruck.logo),
            contentDescription = null,
            modifier = Modifier.size(88.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = foodtruck.name,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.subtitle2
                )
                Box(modifier = Modifier.size(8.dp))
                Text(
                    text = foodtruck.location,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}