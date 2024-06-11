package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM Asteroid ORDER BY date(closeApproachdate) ASC")
    fun getAll(): LiveData<List<Asteroid>>

    @Query("SELECT * FROM Asteroid WHERE closeApproachDate = :closeApproachDate ORDER BY date(closeApproachdate) ASC")
    fun getAsteroidsWith(closeApproachDate: String): LiveData<List<Asteroid>>

    @Query("SELECT * FROM Asteroid WHERE  :start <= closeApproachDate AND closeApproachDate <= :end ORDER BY date(closeApproachdate) ASC")
    fun getAsteroidsBetween(start: String, end: String): LiveData<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: Asteroid)

    @Query("DELETE FROM Asteroid WHERE closeApproachDate < :closeApproachDate")
    fun deleteAsteroidsBefore(closeApproachDate: String)
}