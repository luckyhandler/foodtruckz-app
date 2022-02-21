package de.handler.foodtruckz.feature.overview.data

import de.handler.foodtruckz.library.data.data.Foodtruck

sealed class OverviewUiState {
    object Loading : OverviewUiState()
    data class Success(val foodtruckz: List<Foodtruck>) : OverviewUiState()
    object Error : OverviewUiState()
}
