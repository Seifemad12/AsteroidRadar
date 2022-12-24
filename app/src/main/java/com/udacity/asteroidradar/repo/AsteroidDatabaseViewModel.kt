package com.udacity.asteroidradar.repo

import android.util.Log
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Utils.Constants
import com.udacity.asteroidradar.api.RetrofitService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.getDays.DateHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class AsteroidDatabaseViewModel(private val database: AsteroidDatabase) {

    val asteroidDatabase = Transformations.map(database.asteroidDao().getAllAsteroids()) {
        it.asDomainModel()
    }
    private val start = DateHelper().getToday()
    private val week_list = DateHelper().getWeekDate()
    private val end = week_list[week_list.size - 1]

    val weekAsteroids = Transformations.map(database.asteroidDao().getAllWeekAsteroids(start,end)) {
        it.asDomainModel()
    }
    val todayAsteroid = Transformations.map(database.asteroidDao().getTodayAsteroids(start)) {
        it.asDomainModel()
    }

    suspend fun saveInDatabase() {
        withContext(Dispatchers.IO) {
            try {
                val response =
                    RetrofitService.retrofitService.getAstroids(Constants.API_KEY).await()
                val jsonObject = JSONObject(response)
                val result = parseAsteroidsJsonResult(jsonObject)
                database.asteroidDao().insertAllAsteroids(*result.asDatabaseModel())
            } catch (e: Exception) {
                Log.i("Connection", e.message.toString())
            }

        }
    }

}