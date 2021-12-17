package com.example.shisheo.main.network

import com.example.shisheo.main.model.RestaurantItem
import retrofit2.http.GET

interface RestaurantApi {
    @GET("social/api/web/post/arina/test")
    suspend fun getRestaurants() : ArrayList<RestaurantItem>
}