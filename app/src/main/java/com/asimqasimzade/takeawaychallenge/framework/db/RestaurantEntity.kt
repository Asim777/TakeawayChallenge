package com.asimqasimzade.takeawaychallenge.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class RestaurantEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "bestMatch") val bestMatch: Float,
    @ColumnInfo(name = "newest") val newest: Float,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "distance") val distance: Int,
    @ColumnInfo(name = "popularity") val popularity: Float,
    @ColumnInfo(name = "averagePrice") val averagePrice: Int,
    @ColumnInfo(name = "deliveryCost") val deliveryCosts: Int,
    @ColumnInfo(name = "minCost") val minCost: Int
)