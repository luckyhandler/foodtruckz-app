package de.handler.foodtruckz.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import de.handler.foodtruckz.app.data.MainUiState
import de.handler.foodtruckz.app.data.MainViewModelImpl
import de.handler.foodtruckz.app.ui.theme.FoodtruckzTheme
import de.handler.foodtruckz.details.FoodtruckDetails
import de.handler.foodtruckz.feature.overview.FoodtruckOverview

class MainActivity : AppCompatActivity() {
    private lateinit var locationProvider: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModelImpl::class.java)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)
        when {
            checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED -> requestLocationPermission(
                viewModel
            )
            else -> viewModel.showOverview(locationProvider)
        }

        viewModel.uiState.observe(this) {
            setContent {
                FoodtruckzTheme {
                    // A surface container using the 'background' color from the theme
                    Surface {
                        when (it) {
                            is MainUiState.ShowDetails -> FoodtruckDetails(
                                foodtruck = it.foodtruck,
                            )
                            is MainUiState.ShowOverview -> FoodtruckOverview(
                                activity = this,
                                longitude = it.longitude,
                                latitude = it.latitude,
                                onClick = {
                                    viewModel.showDetails(foodtruck = it)
                                })
                            MainUiState.Error -> {}
                            MainUiState.Loading -> CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }

    private fun requestLocationPermission(viewModel: MainViewModelImpl) {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ->
                    viewModel.showOverview(locationProvider)
                permissions.getOrDefault(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    false
                ) -> viewModel.showOverview(locationProvider)
                else -> {}
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}