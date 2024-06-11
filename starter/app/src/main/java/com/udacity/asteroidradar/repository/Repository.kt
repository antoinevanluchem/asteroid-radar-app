package com.udacity.asteroidradar.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.database.NasaDatabase
import com.udacity.asteroidradar.database.getLastDayOfWeekFormattedDate
import com.udacity.asteroidradar.database.getTodayFormattedDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class NasaRepository(application: Context){

    private val nasaDatabase = NasaDatabase.getInstance(application)

    var asteroids: LiveData<List<Asteroid>> = nasaDatabase.asteroidDao.getAll()
    var todayAsteroids: LiveData<List<Asteroid>> = nasaDatabase.asteroidDao.getAsteroidsWith(closeApproachDate = getTodayFormattedDate())
    var weekAsteroids: LiveData<List<Asteroid>> = nasaDatabase.asteroidDao.getAsteroidsWithCloseApproachDateBetween(start = getTodayFormattedDate(), end = getLastDayOfWeekFormattedDate())
    var pictureOfDay: LiveData<PictureOfDay> = nasaDatabase.pictureOfDayDao.get()

    /** This function refreshes the `asteroids`, `todayAsteroids` and `weekAsteroids` attributes. **/
    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val asteroids = Network.nasa.getAsteroids()
                nasaDatabase.asteroidDao.insertAll(*asteroids.toTypedArray())
                Timber.i("Successfully refreshed the asteroids: $asteroids")
            } catch (e: Exception) {
                Timber.e("Something went wrong while refreshing the asteroids: $e")
            }
        }
    }

    suspend fun refreshPictureOfDay() {
        withContext(Dispatchers.IO) {
            try {
                val pictureOfDay = Network.nasa.getPictureOfDay()
                nasaDatabase.pictureOfDayDao.insert(pictureOfDay)
                Timber.i("Successfully fetched pictureOfDay: ${pictureOfDay.title}")
            } catch (e: Exception) {
                Timber.e("Something went wrong while fetching pictureOfDay: $e")
            }
        }
    }

    suspend fun deletePreviousDaysAsteroids() {
        withContext(Dispatchers.IO) {
            nasaDatabase.asteroidDao.deleteAsteroidsBefore(closeApproachDate = getTodayFormattedDate())
        }
    }
}