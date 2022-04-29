package de.handler.foodtruckz.app.data

import android.location.Location
import de.handler.foodtruckz.library.data.data.Foodtruck

sealed class MainUiState {
    class ShowOverview(val latitude: Double, val longitude: Double) : MainUiState()
    class ShowDetails(val foodtruck: Foodtruck) : MainUiState()
    object Error : MainUiState()
    object Loading : MainUiState()
}
