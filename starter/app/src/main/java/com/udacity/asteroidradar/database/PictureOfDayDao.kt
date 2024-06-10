package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.PictureOfDay

@Dao
interface PictureOfDayDao {
    @Query("SELECT * FROM PictureOfDay")
    fun get(): LiveData<PictureOfDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pic: PictureOfDay)
}