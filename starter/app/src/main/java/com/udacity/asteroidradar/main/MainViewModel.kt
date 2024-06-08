package com.udacity.asteroidradar.main

import NasaDatabase
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

//    private val database = NasaDatabase.getInstance(application)
    val asteroidsRepository = AsteroidsRepository()

    init {
        viewModelScope.launch {
            asteroidsRepository.refreshAsteroids()
        }
    }

//    class Factory(val app: Application) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return MainViewModel(app) as T
//            }
//            throw IllegalArgumentException("Unable to construct viewmodel")
//        }
//    }
}