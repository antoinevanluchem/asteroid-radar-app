package com.udacity.asteroidradar.repository

import android.util.Log
import com.udacity.asteroidradar.api.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class AsteroidsRepository {
    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val asteroids = Network.nasa.getAsteroids()
                Timber.i("Successfully refreshed the asteroids: $asteroids")
            } catch (e: Exception) {
                Timber.i("Something went wrong while refreshing the asteroids: $e")
            }
        }
    }
}