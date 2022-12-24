package com.udacity.asteroidradar.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.udacity.asteroidradar.datamodels.Asteroid

class AsteroidConverter {

    @TypeConverter
    fun fromJsonAsteroidData(asteroidData: AsteroidData):String{
        return Gson().toJson(asteroidData)
    }

    @TypeConverter
    fun toJsonAsteroidData(list: String):AsteroidData{
        return Gson().fromJson(list,AsteroidData::class.java)
    }
    @TypeConverter
    fun fromJsonAsteroid(asteroidData: Asteroid):String{
        return Gson().toJson(asteroidData)
    }

    @TypeConverter
    fun toJsonAsteroid(list: String): Asteroid {
        return Gson().fromJson(list, Asteroid::class.java)
    }
    @TypeConverter
    fun fromJson(list: ArrayList<Asteroid>):String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toJson(list: String):ArrayList<Asteroid>{
        val resp =object :TypeToken<ArrayList<Asteroid>>(){}.type
        return Gson().fromJson(list,resp)
    }
}