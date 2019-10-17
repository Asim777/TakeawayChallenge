package com.asimqasimzade.takeawaychallenge.framework.db

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FavoriteRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteRestaurant(favoriteRestaurant: RestaurantEntity)

    @Query("SELECT * FROM favorites")
    fun getFavoriteRestaurants(): Single<List<RestaurantEntity>>

    @Delete
    fun removeFavoriteRestaurant(favorite: RestaurantEntity)
}
