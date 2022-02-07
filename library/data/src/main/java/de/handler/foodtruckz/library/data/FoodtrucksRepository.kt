package de.handler.foodtruckz.library.data

import de.handler.foodtruckz.library.data.data.Foodtruck
import java.util.*

interface FoodtrucksRepository {
    suspend fun getFoodtruckz(): List<Foodtruck>
}

class FoodtrucksRepositoryImpl(val manager: FoodtrucksManager) : FoodtrucksRepository {
    private val foodtrucks = mutableListOf<Foodtruck>()
    private var cacheTimeStamp: Date = Date()


    override suspend fun getFoodtruckz(): List<Foodtruck> =
        if (foodtrucks.isNotEmpty() && cacheTimeStamp.day == Date().day) {
            foodtrucks
        } else {
            foodtrucks.clear()
            foodtrucks.addAll(manager.getFoodtrucks())
            foodtrucks
        }
}