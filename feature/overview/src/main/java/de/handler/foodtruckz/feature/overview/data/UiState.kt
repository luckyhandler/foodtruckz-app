package de.handler.foodtruckz.feature.overview.data

import de.handler.foodtruckz.library.data.data.Foodtruck

sealed class UiState {
    object Loading : UiState()
    data class Success(val foodtruckz: List<Foodtruck>) : UiState()
    object Error : UiState()
}
