package de.handler.foodtruckz.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.handler.foodtruckz.library.data.data.Foodtruck

abstract class MainViewModel : ViewModel() {
    abstract val uiState: LiveData<MainUiState>

    abstract fun showOverview()
    abstract fun showDetails(foodtruck: Foodtruck)
}

class MainViewModelImpl : MainViewModel() {
    override val uiState = MutableLiveData<MainUiState>()

    override fun showOverview() {
        uiState.value = MainUiState.ShowOverview
    }

    override fun showDetails(foodtruck: Foodtruck) {
        uiState.value = MainUiState.ShowDetails(foodtruck)
    }
}