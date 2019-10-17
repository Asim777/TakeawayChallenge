package com.asimqasimzade.takeawaychallenge.data

import com.asimqasimzade.takeawaychallenge.data.model.response.RestaurantsResponse
import io.reactivex.Single

interface RestaurantsDataSource {
     fun getAll(): Single<RestaurantsResponse>
}