package com.example.shisheo.main.intent

sealed class RestaurantScreenIntent {
    class GetRestaurantList() : RestaurantScreenIntent()
}