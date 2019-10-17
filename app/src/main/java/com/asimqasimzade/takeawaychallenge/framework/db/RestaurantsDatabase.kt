package com.asimqasimzade.takeawaychallenge.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantsDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "restaurants.db"

        private var instance: RestaurantsDatabase? = null

        private fun create(context: Context): RestaurantsDatabase =
            Room.databaseBuilder(context, RestaurantsDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): RestaurantsDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun favoriteRestaurantDao(): FavoriteRestaurantDao
}
