package de.handler.foodtruckz.feature.overview.data

sealed class UiState {
    object Loading : UiState()
    data class Success(val foodtruckz: List<Foodtruck>) : UiState()
    object Error : UiState()
}
