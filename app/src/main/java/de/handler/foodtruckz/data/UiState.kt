package de.handler.foodtruckz.data

sealed class UiState {
    object Loading : UiState()
    data class Success(val foodtruckz: List<Foodtruck>) : UiState()
    object Error : UiState()
}
