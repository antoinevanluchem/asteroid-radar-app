package com.udacity.asteroidradar.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val asteroidsRepository = AsteroidsRepository()

    init {
        viewModelScope.launch {
            asteroidsRepository.refreshAsteroids()
        }
    }
}