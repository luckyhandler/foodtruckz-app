package de.handler.foodtruckz.feature.overview.data

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.handler.foodtruckz.library.data.FoodtrucksManagerImpl
import de.handler.foodtruckz.library.data.FoodtrucksRepositoryImpl
import de.handler.foodtruckz.library.data.data.Foodtruck
import kotlinx.coroutines.launch

abstract class FoodtruckViewModel : ViewModel() {
    abstract val overviewUiState: LiveData<OverviewUiState>

    abstract fun fetchTruckz(latitude: Double, longitude: Double)
    abstract fun openDetails(foodtruck: Foodtruck)
}

class FoodtruckViewModelImpl : FoodtruckViewModel() {

    override val overviewUiState = MutableLiveData<OverviewUiState>()

    private val manager = FoodtrucksManagerImpl()
    private val repo = FoodtrucksRepositoryImpl(manager = manager)

    override fun fetchTruckz(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            overviewUiState.value = OverviewUiState.Loading

            try {
                val foodtrucks = repo.getFoodtruckz(latitude = latitude, longitude = longitude)
                overviewUiState.value = OverviewUiState.Success(foodtruckz = foodtrucks)
            } catch (e: Exception) {
                // if exception occurs emit error and exit execution
                overviewUiState.value = OverviewUiState.Error
                return@launch
            }
        }
    }

    override fun openDetails(foodtruck: Foodtruck) {
        viewModelScope.launch {

        }
    }
}