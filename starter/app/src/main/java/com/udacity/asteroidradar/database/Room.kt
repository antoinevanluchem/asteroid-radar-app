package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.AsteroidDao

@Database(entities = [Asteroid::class, PictureOfDay::class], version = 1)
abstract class NasaDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
    abstract val pictureOfDayDao: PictureOfDayDao

    companion object {
        private lateinit var INSTANCE: NasaDatabase

        fun getInstance(context: Context): NasaDatabase {
            synchronized(NasaDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NasaDatabase::class.java,
                        "nasa_database").build()
                }
            }
            return INSTANCE
        }
    }
}





