package com.udacity.asteroidradar.api

/**
 * The object `Network` bundles all network services, such that they can be called as `Network.networkService` in the app.
 * This makes the code more readable and better encapsulated.
 */
object Network {
    val nasa = NasaService
}