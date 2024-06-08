package com.udacity.asteroidradar.repository

import android.util.Log
import com.udacity.asteroidradar.api.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidsRepository {
    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val asteroids = Network.nasa.getAsteroids()
            } catch (e: Exception) {
                Log.e("Failure: ", e.toString())
            }
        }
    }
}