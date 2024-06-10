package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object NasaService {
    //
    // Retrofit
    //
    private interface ServiceInterface {
        @GET("neo/rest/v1/feed")
        suspend fun getAsteroids(
            @Query("api_key") api_key: String
        ): String

        @GET("planetary/apod")
        suspend fun getPictureOfDay(
            @Query("api_key") api_key: String
        ): PictureOfDay

    }

    private val service = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(ServiceInterface::class.java)

    //
    // Public interface
    //
    suspend fun getAsteroids() : List<Asteroid> {
        val asteroidsJsonResult = JSONObject(service.getAsteroids(BuildConfig.NASA_API_KEY))
        return parseAsteroidsJsonResult(asteroidsJsonResult)
    }

    suspend fun getPictureOfDay() : PictureOfDay {
        return service.getPictureOfDay(BuildConfig.NASA_API_KEY)
    }
}