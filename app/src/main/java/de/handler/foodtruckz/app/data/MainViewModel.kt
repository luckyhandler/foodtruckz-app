package de.handler.foodtruckz.app.data

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import de.handler.foodtruckz.library.data.data.Foodtruck
import timber.log.Timber

abstract class MainViewModel : ViewModel() {
    abstract val uiState: LiveData<MainUiState>

    abstract fun showOverview(fusedLocationClient: FusedLocationProviderClient)
    abstract fun showDetails(foodtruck: Foodtruck)
}

class MainViewModelImpl : MainViewModel() {
    override val uiState = MutableLiveData<MainUiState>()

    @SuppressLint("MissingPermission")
    override fun showOverview(fusedLocationClient: FusedLocationProviderClient) {
        uiState.value = MainUiState.Loading
        Timber.d("Getting location")
        fusedLocationClient.lastLocation.addOnSuccessListener {
            uiState.value =
                MainUiState.ShowOverview(latitude = it.latitude, longitude = it.longitude)
        }.addOnFailureListener { Timber.e(it) }
    }

    override fun showDetails(foodtruck: Foodtruck) {
        uiState.value = MainUiState.ShowDetails(foodtruck)
    }
}