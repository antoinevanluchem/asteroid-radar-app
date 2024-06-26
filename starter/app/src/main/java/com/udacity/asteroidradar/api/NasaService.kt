package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
        ): String

    }

    /** Keep the service encapsulated such that we can provide our public interface to the app. */
    private val service = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(ServiceInterface::class.java)

    //
    // Public facade
    //
    suspend fun getAsteroids() : List<Asteroid> {
        val asteroidsJsonResult = JSONObject(service.getAsteroids(BuildConfig.NASA_API_KEY))
        return parseAsteroidsJsonResult(asteroidsJsonResult)
    }

    suspend fun getPictureOfDay() : PictureOfDay {
        val response = service.getPictureOfDay(BuildConfig.NASA_API_KEY)

        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(PictureOfDay::class.java)
            .fromJson(response)
            ?:
            PictureOfDay("", "", "")
    }
}