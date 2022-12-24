package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAsteroids(vararg asteroids:AsteroidData)

    @Query("select * from asteroid_table order by closeApproachDate")
    fun getAllAsteroids():LiveData<List<AsteroidData>>

    @Query("select * from asteroid_table where closeApproachDate between :start and :end order by closeApproachDate")
    fun getAllWeekAsteroids(start:String,end:String):LiveData<List<AsteroidData>>

    @Query("select * from asteroid_table where closeApproachDate = :start order by closeApproachDate")
    fun getTodayAsteroids(start:String):LiveData<List<AsteroidData>>

}