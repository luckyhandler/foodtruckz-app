package de.handler.foodtruckz.feature.overview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.handler.foodtruckz.library.data.FoodtrucksManagerImpl
import de.handler.foodtruckz.library.data.FoodtrucksRepositoryImpl
import de.handler.foodtruckz.library.data.data.Foodtruck
import kotlinx.coroutines.launch

abstract class FoodtruckViewModel : ViewModel() {
    abstract val uiState: LiveData<UiState>

    abstract fun fetchTruckz()
    abstract fun openDetails(foodtruck: Foodtruck)
}

class FoodtruckViewModelImpl : FoodtruckViewModel() {

    override val uiState = MutableLiveData<UiState>()

    private val manager = FoodtrucksManagerImpl()
    private val repo = FoodtrucksRepositoryImpl(manager = manager)

    override fun fetchTruckz() {
        viewModelScope.launch {
            uiState.value = UiState.Loading

            try {
                val foodtrucks = repo.getFoodtruckz()
                uiState.value = UiState.Success(foodtruckz = foodtrucks)
            } catch (e: Exception) {
                // if exception occurs emit error and exit execution
                uiState.value = UiState.Error
                return@launch
            }
        }
    }

    override fun openDetails(foodtruck: Foodtruck) {
        viewModelScope.launch {

        }
    }
}