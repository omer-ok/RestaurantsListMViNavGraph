package com.example.shisheo.main.repository

import android.util.Log
import com.example.shisheo.main.model.RestaurantItem
import com.example.shisheo.main.network.RestaurantApi
import com.task.nycnewsarticles.foundatiion.utilz.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantRepo
@Inject
constructor(private val restaurantApi: RestaurantApi) {

    fun getRestaurant(): Flow<DataState<ArrayList<RestaurantItem>>> = flow {
        emit(DataState.Loading)
        try {

            val response: ArrayList<RestaurantItem> = restaurantApi.getRestaurants()
            emit(DataState.Success(response))
        } catch (e: Exception) {
            Log.i("Excep",e.toString())
            emit(DataState.Error(e))
        }
    }
}