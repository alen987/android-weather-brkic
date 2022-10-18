package com.example.weatherbrkic.data.network

import com.example.weatherbrkic.data.model.Forecast
import com.example.weatherbrkic.data.model.Location
import com.example.weatherbrkic.data.model.Report
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://dataservice.accuweather.com/locations/v1/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .client(getLoggingHttpClient())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private fun getLoggingHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
    return builder.build()
}

interface AccuWeatherApiService {
    /**
     * Returns a [List] of [Location] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "locations" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("cities/autocomplete")
    suspend fun getLocations(@Query("apikey") apikey: String, @Query("q") q: String): List<Location>

    @GET("{locationKey}")
    suspend fun searchByKey(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apikey: String
    ): Report

    @GET("/forecasts/v1/daily/1day/{locationKey}")
    suspend fun getForecastForOneDay(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apikey: String
    ): Forecast

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object AccuWeatherApi {
    val retrofitService: AccuWeatherApiService by lazy { retrofit.create(AccuWeatherApiService::class.java) }
}
