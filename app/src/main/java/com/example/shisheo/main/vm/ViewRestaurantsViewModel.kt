package com.example.shisheo.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shisheo.main.intent.RestaurantScreenIntent
import com.example.shisheo.main.model.RestaurantItem
import com.example.shisheo.main.repository.RestaurantRepo
import com.task.nycnewsarticles.foundatiion.utilz.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
class ViewRestaurantsViewModel
constructor(
    private val restaurantRepo: RestaurantRepo
): ViewModel() {
    val userIntent = Channel<RestaurantScreenIntent>(Channel.UNLIMITED)

    private val _restaurantsResponse: MutableLiveData<DataState<ArrayList<RestaurantItem>>> = MutableLiveData()
    val restaurantsResponse: LiveData<DataState<ArrayList<RestaurantItem>>>
        get() = _restaurantsResponse


    init {
        handleIntent()
    }

    // The ViewModel handles these events and communicates with the Model.
    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is RestaurantScreenIntent.GetRestaurantList -> getRestaurant()
                }
            }
        }
    }

    //As a result, the ViewModel updates the View with new states and is then displayed to the user.
    fun getRestaurant() {
        viewModelScope.launch {
            restaurantRepo.getRestaurant()
                .onEach { dataState ->
                    _restaurantsResponse.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}

