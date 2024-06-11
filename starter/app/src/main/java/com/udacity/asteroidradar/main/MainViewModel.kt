package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.repository.NasaRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val nasaRepository = NasaRepository(application)

    init {
        viewModelScope.launch {
            nasaRepository.refreshAsteroids()
            nasaRepository.refreshPictureOfDay()

            // Show all asteroids on startup
            showSavedAsteroids()
        }
    }

    val pictureOfDay = nasaRepository.pictureOfDay

    //
    // Asteroids
    //
    private val _asteroids = MediatorLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    private var currentSource: LiveData<List<Asteroid>>? = null

    /** MediatorLiveData does not allow the same source to be added multiple times with different observers.
     To handle this, remove the source before adding a new one. **/
    private fun setAsteroidsSource(newSource: LiveData<List<Asteroid>>) {
        currentSource?.let { _asteroids.removeSource(it) }
        currentSource = newSource
        _asteroids.addSource(newSource) {
            _asteroids.value = it
        }
    }

    fun showSavedAsteroids() {
        setAsteroidsSource(nasaRepository.asteroids)
    }

    fun showTodayAsteroids() {
        setAsteroidsSource(nasaRepository.todayAsteroids)
    }

    fun showWeekAsteroids() {
        setAsteroidsSource(nasaRepository.weekAsteroids)
    }

    //
    // Navigate to details
    //
    private val _navigateToDetails = MutableLiveData<Asteroid>()
    val navigateToDetails: LiveData<Asteroid>
        get() = _navigateToDetails

    fun displayDetails(asteroid: Asteroid) {
        _navigateToDetails.value = asteroid
    }
    fun displayDetailsComplete() {
        _navigateToDetails.value = null
    }

    //
    // Utils
    //
    class Factory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}