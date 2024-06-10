package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.NasaDatabase
import com.udacity.asteroidradar.repository.NasaRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = NasaDatabase.getInstance(application)
    private val nasaRepository = NasaRepository(database)
    val asteroids = nasaRepository.asteroids
    val pictureOfDay = nasaRepository.pictureOfDay

    private val _navigateToDetails = MutableLiveData<Asteroid>()

    // The external immutable LiveData for the navigation property
    val navigateToDetails: LiveData<Asteroid>
        get() = _navigateToDetails

    init {
        viewModelScope.launch {
            nasaRepository.refreshAsteroids()
            nasaRepository.refreshPictureOfDay()
        }
    }

    fun displayDetails(asteroid: Asteroid) {
        _navigateToDetails.value = asteroid
    }
    fun displayDetailsComplete() {
        _navigateToDetails.value = null
    }


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