package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.repository.NasaRepository
import retrofit2.HttpException

class DeleteDataWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "DeleteDataWorker"
    }

    override suspend fun doWork(): Result {
        val repository = NasaRepository(applicationContext)
        return try {
            repository.deletePreviousDaysAsteroids()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }
}