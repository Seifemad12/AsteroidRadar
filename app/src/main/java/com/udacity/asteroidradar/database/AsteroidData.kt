package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.datamodels.Asteroid

@Entity(tableName = "asteroid_table")
data class AsteroidData(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

fun List<AsteroidData>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}
fun List<Asteroid>.asDatabaseModel(): Array<AsteroidData> {
   return map {
       AsteroidData(
           id = it.id,
           codename = it.codename,
           closeApproachDate = it.closeApproachDate,
           absoluteMagnitude = it.absoluteMagnitude,
           estimatedDiameter = it.estimatedDiameter,
           relativeVelocity = it.relativeVelocity,
           distanceFromEarth = it.distanceFromEarth,
           isPotentiallyHazardous = it.isPotentiallyHazardous
       )
   }.toTypedArray()
}