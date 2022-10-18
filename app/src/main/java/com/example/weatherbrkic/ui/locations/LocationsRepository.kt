package com.example.weatherbrkic.ui.locations

import com.example.weatherbrkic.BuildConfig
import com.example.weatherbrkic.data.model.Forecast
import com.example.weatherbrkic.data.model.Location
import com.example.weatherbrkic.data.model.Report
import com.example.weatherbrkic.data.network.AccuWeatherApiService

class LocationsRepository(
    private val service: AccuWeatherApiService
) {
    var apiKey: String = BuildConfig.ACCU_WEATHER_API_KEY

    suspend fun getLocations(searchValue:String): List<Location> {
       return service.getLocations(apiKey, searchValue)
    }

    suspend fun searchByKey(locationKey:String): Report {
        return service.searchByKey(locationKey,apiKey)
    }

    suspend fun getForecastForOneDay(locationKey:String): Forecast {
        return service.getForecastForOneDay(locationKey,apiKey)
    }
}