package de.handler.foodtruckz.library.data

import de.handler.foodtruckz.library.data.data.AuthInterceptor
import de.handler.foodtruckz.library.data.data.Foodtruck
import de.handler.foodtruckz.library.data.data.FoodtruckzService
import de.handler.foodtruckz.library.data.data.extension.filterForDate
import de.handler.foodtruckz.library.data.data.extension.mapToModelList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.ZonedDateTime

interface FoodtrucksManager {
    suspend fun getFoodtrucks(latitude: Double, longitude: Double): List<Foodtruck>
}

class FoodtrucksManagerImpl : FoodtrucksManager {
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val retrofit = Retrofit.Builder()
        .client(okHttp)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://www.craftplaces-business.com/api/")
        .build()
    private val service = retrofit.create(FoodtruckzService::class.java)

    override suspend fun getFoodtrucks(latitude: Double, longitude: Double): List<Foodtruck> {
        val foodtruckz =
            service.fetchFoodtruckz(latitude = latitude, longitude = longitude)
        // Filter foodtrucks for specified date
        val now = ZonedDateTime.now()
        val foodtrucksForDate = foodtruckz.filterForDate(
            from = ZonedDateTime.of(
                now.year,
                now.monthValue,
                now.dayOfMonth,
                10,
                0,
                0,
                0,
                now.zone
            ),
            to = ZonedDateTime.of(
                now.year,
                now.monthValue,
                now.dayOfMonth,
                15,
                0,
                0,
                0,
                now.zone
            ).plusDays(7)
        )
        return foodtrucksForDate.mapToModelList()
    }
}