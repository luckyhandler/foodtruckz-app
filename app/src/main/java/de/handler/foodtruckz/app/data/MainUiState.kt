package de.handler.foodtruckz.app.data

import de.handler.foodtruckz.library.data.data.Foodtruck

sealed class MainUiState {
    object ShowOverview : MainUiState()
    class ShowDetails(val foodtruck: Foodtruck) : MainUiState()
    object Error : MainUiState()
}
