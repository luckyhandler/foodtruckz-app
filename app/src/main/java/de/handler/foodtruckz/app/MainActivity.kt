package de.handler.foodtruckz.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import de.handler.foodtruckz.app.ui.theme.FoodtruckzTheme
import de.handler.foodtruckz.feature.overview.FoodtruckOverview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodtruckzTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    FoodtruckOverview(activity = this)
                }
            }
        }
    }
}