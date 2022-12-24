package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Utils.Constants
import com.udacity.asteroidradar.api.RetrofitService
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.datamodels.PictureOfDay
import com.udacity.asteroidradar.repo.AsteroidDatabaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val retrofit = RetrofitService.retrofitService
    private val database = AsteroidDatabase.getDatabase(application)
    private val asteroidRepo = AsteroidDatabaseViewModel(database)

    val asteroidDatabase = asteroidRepo.asteroidDatabase
    val allWeekAsteroid = asteroidRepo.weekAsteroids
    val todayAsteroid = asteroidRepo.todayAsteroid

    /*private val _picLiveData = MutableLiveData<String>()
    val picLiveData: LiveData<String>
        get() = _picLiveData*/

    private val _picLiveData = MutableLiveData<PictureOfDay>()
    val picLiveData: LiveData<PictureOfDay>
        get() = _picLiveData


    init {
        viewModelScope.launch {
            asteroidRepo.saveInDatabase()
            setPicOfToday()
        }
    }
    private suspend fun setPicOfToday(){
        try{
            _picLiveData.value = retrofit.getPicOfDay(Constants.API_KEY).await()
        }
        catch (e:Exception){
            Log.i("Connection","must be an internet connection!")
        }
    }

    /*fun setIsPotentiallyHazardous(){
        _isHazardous.value =
    }*/
}