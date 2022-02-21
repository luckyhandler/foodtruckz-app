package de.handler.foodtruckz.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import de.handler.foodtruckz.app.data.MainUiState
import de.handler.foodtruckz.app.data.MainViewModelImpl
import de.handler.foodtruckz.app.ui.theme.FoodtruckzTheme
import de.handler.foodtruckz.details.FoodtruckDetails
import de.handler.foodtruckz.feature.overview.FoodtruckOverview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModelImpl::class.java)

        viewModel.showOverview()

        viewModel.uiState.observe(this) {
            setContent {
                FoodtruckzTheme {
                    // A surface container using the 'background' color from the theme
                    Surface {
                        when (it) {
                            is MainUiState.ShowDetails -> FoodtruckDetails(
                                foodtruck = it.foodtruck,
                            )
                            MainUiState.ShowOverview -> FoodtruckOverview(
                                activity = this,
                                onClick = {
                                    viewModel.showDetails(foodtruck = it)
                                })
                            MainUiState.Error -> TODO()
                        }
                    }
                }
            }
        }
    }
}