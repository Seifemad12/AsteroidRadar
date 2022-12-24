package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.repo.AsteroidDatabaseViewModel
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params){
    companion object {
        const val WORK_NAME = "RefreshAsteroidWorker"
    }
    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getDatabase(applicationContext)
        val asteroidRepo = AsteroidDatabaseViewModel(database)

        return try {
            asteroidRepo.saveInDatabase()
            Result.success()
        }
        catch (ex: HttpException){
            Result.retry()
        }
    }
}