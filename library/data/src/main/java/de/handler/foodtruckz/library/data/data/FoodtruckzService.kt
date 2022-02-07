package de.handler.foodtruckz.library.data.data

import de.handler.foodtruckz.library.data.data.Foodtruckz
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodtruckzService {
    @GET("locations/getTours.json")
    suspend fun fetchFoodtruckz(
        @Query("longitude") longitude: String,
        @Query("latitude") latitude: String,
        @Query("rd") rd: String = "2",
        @Query("version") version: String = "2",
    ): Foodtruckz
}