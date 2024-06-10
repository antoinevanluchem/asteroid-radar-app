package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.database.NasaDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class AsteroidsRepository(private val nasaDatabase: NasaDatabase){
    var asteroids: LiveData<List<Asteroid>> = nasaDatabase.asteroidDao.getAll()

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val asteroids = Network.nasa.getAsteroids()
                nasaDatabase.asteroidDao.insertAll(*asteroids.toTypedArray())
                Timber.i("Successfully refreshed the asteroids: $asteroids")
            } catch (e: Exception) {
                Timber.i("Something went wrong while refreshing the asteroids: $e")
            }
        }
    }
}