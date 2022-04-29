@file:OptIn(ExperimentalCoilApi::class)

package de.handler.foodtruckz.feature.overview

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import de.handler.foodtruckz.feature.overview.data.FoodtruckViewModelImpl
import de.handler.foodtruckz.feature.overview.data.OverviewUiState
import de.handler.foodtruckz.library.data.data.Foodtruck

@Composable
fun FoodtruckOverview(
    activity: ComponentActivity,
    onClick: (Foodtruck) -> Unit,
    latitude: Double,
    longitude: Double,
) {
    val viewModel = ViewModelProvider(activity).get(FoodtruckViewModelImpl::class.java)

    viewModel.fetchTruckz(latitude = latitude, longitude = longitude)

    viewModel.overviewUiState.observe(activity) {
        activity.setContent {
            FoodtruckzContent(onClick = onClick, overviewUiState = it)
        }
    }
}

@Composable
private fun FoodtruckzContent(overviewUiState: OverviewUiState, onClick: (Foodtruck) -> Unit) =
    when (overviewUiState) {
        is OverviewUiState.Success -> Column {
            overviewUiState.foodtruckz.forEach { FoodtruckItem(foodtruck = it, onClick = onClick) }
        }
        OverviewUiState.Loading -> Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        OverviewUiState.Error -> Text(text = "error")
    }

@Composable
fun FoodtruckItem(foodtruck: Foodtruck, onClick: (Foodtruck) -> Unit) {
    Box(modifier = Modifier.clickable { onClick(foodtruck) }) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text(
                text = foodtruck.time,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle2
            )
            Box(modifier = Modifier.size(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberImagePainter(foodtruck.logo),
                    contentDescription = null,
                    modifier = Modifier.size(88.dp)
                )
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
}
