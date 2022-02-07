package de.handler.foodtruckz.feature.overview

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import de.handler.foodtruckz.feature.overview.data.FoodtruckViewModelImpl
import de.handler.foodtruckz.feature.overview.data.UiState

@Composable
fun FoodtruckOverview(activity: ComponentActivity) {
    val viewModel = ViewModelProvider(activity).get(FoodtruckViewModelImpl::class.java)

    viewModel.fetchTruckz()

    viewModel.uiState.observe(activity) {
        activity.setContent {
            FoodtruckzContent(uiState = it)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun FoodtruckzContent(uiState: UiState) = when (uiState) {
    is UiState.Success -> Column {
        uiState.foodtruckz.forEach { foodtruck ->
            Row(modifier = Modifier.padding(all = 16.dp)) {
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
                        style = TextStyle.Default.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Box(modifier = Modifier.size(8.dp))
                    Text(
                        text = foodtruck.location,
                        textAlign = TextAlign.Start,
                    )
                }
            }

        }
    }
    UiState.Loading -> Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
    UiState.Error -> Text(text = "error")
}