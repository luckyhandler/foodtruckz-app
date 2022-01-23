package de.handler.foodtruckz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.handler.foodtruckz.data.extension.filterForDate
import de.handler.foodtruckz.data.extension.mapToModelList
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.ZonedDateTime


const val longitude: String = "11.012630409289772"
const val latitude: String = "49.46819689534032"

abstract class FoodtruckViewModel : ViewModel() {
    abstract val uiState: LiveData<UiState>

    abstract fun fetchTruckz()
}

class FoodtruckViewModelImpl : FoodtruckViewModel() {
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

    override val uiState = MutableLiveData<UiState>()

    private val service = retrofit.create(FoodtruckzService::class.java)

    override fun fetchTruckz() {
        viewModelScope.launch {
            uiState.value = UiState.Loading

            val foodtruckz = try {
                service.fetchFoodtruckz(latitude = latitude, longitude = longitude)
            } catch (e: Exception) {
                // if exception occurs emit error and exit execution
                uiState.value = UiState.Error
                return@launch
            }

            // Filter foodtrucks for specified date
            val now = ZonedDateTime.now()
            val foodtrucksForToday = foodtruckz.filterForDate(
                from = ZonedDateTime.of(
                    now.year,
                    now.monthValue,
                    now.dayOfMonth,
                    10,
                    0,
                    0,
                    0,
                    now.zone),
                to = ZonedDateTime.of(
                    now.year,
                    now.monthValue,
                    now.dayOfMonth,
                    15,
                    0,
                    0,
                    0,
                    now.zone).plusDays(1)
            )
            val foodtrucks = foodtrucksForToday.mapToModelList()

            uiState.value = UiState.Success(foodtruckz = foodtrucks)
        }
    }
}