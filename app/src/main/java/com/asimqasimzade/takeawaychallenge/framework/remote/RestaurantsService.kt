package com.asimqasimzade.takeawaychallenge.framework.remote

import com.asimqasimzade.takeawaychallenge.data.model.response.RestaurantsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RestaurantsService {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/Asim777/demo/master/"
    }

    @GET("db.json")
    fun getRestaurants(): Single<RestaurantsResponse>
}