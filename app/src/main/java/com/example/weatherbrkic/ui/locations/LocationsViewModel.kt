package com.example.weatherbrkic.ui.locations

import androidx.lifecycle.*
import com.example.weatherbrkic.data.model.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: LocationsRepository) : ViewModel() {

    val status = MutableLiveData<AccuWeatherApiStatus>()
    val locations = MutableLiveData<List<Location>>(listOf())
    val searchValue = MutableLiveData("")
    val minimumTemp = MutableLiveData<Double>()
    val maximumTemp = MutableLiveData<Double>()

    init {
        getCities()
    }

    private fun getCities() {

        viewModelScope.launch {
            status.value = AccuWeatherApiStatus.LOADING
            try {
                searchValue.asFlow().collect {
                    if (it?.length!! > 3) {
                        locations.value = repository.getLocations(it)
                        status.value = AccuWeatherApiStatus.DONE
                    }
                }
            } catch (e: Exception) {
                status.value = AccuWeatherApiStatus.ERROR
                locations.value = listOf()
                println(e)
            }
        }
    }

    fun getForecastForOneDay(key: String) {
        viewModelScope.launch {
            try {
                val response = repository.getForecastForOneDay(key);
                if (response.DailyForecasts.isNotEmpty()) {
                    val temperature: Temperature = response.DailyForecasts[0].Temperature
                    minimumTemp.value = temperature.Minimum.Value
                    maximumTemp.value = temperature.Maximum.Value
                }
            } catch (e: Exception) {
                status.value = AccuWeatherApiStatus.ERROR
                locations.value = listOf()
                println(e)
            }
        }
    }
}