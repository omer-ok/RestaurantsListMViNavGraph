package com.example.shisheo.main.di

import com.example.shisheo.main.network.RestaurantApi
import com.example.shisheo.main.repository.RestaurantRepo
import com.example.shisheo.main.vm.ViewRestaurantsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object ViewResturantModule {

    @Singleton
    @Provides
    fun provideRestaurantApi(retrofit: Retrofit.Builder):RestaurantApi{
        return retrofit.build()
            .create(RestaurantApi::class.java)
    }
    @Singleton
    @Provides
    fun provideRestaurantRepo(
        restaurantApi: RestaurantApi
    ):RestaurantRepo{
        return RestaurantRepo(restaurantApi)
    }

    @Singleton
    @Provides
    fun provideViewRestaurantViewModel(
        restaurantRepo: RestaurantRepo
    ):ViewRestaurantsViewModel{
        return ViewRestaurantsViewModel(restaurantRepo)
    }
}