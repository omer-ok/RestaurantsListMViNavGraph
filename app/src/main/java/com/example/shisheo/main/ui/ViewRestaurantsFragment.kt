package com.example.shisheo.main.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shisheo.R
import com.example.shisheo.main.adapters.ResturantsAdapter
import com.example.shisheo.main.intent.RestaurantScreenIntent
import com.example.shisheo.main.model.RestaurantItem
import com.example.shisheo.main.repository.RestaurantRepo
import com.example.shisheo.main.vm.ViewRestaurantsViewModel
import com.media.shaadoow.foundation.mvi.BaseFragment
import com.task.nycnewsarticles.foundatiion.utilz.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_restaurants_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
@AndroidEntryPoint
class ViewRestaurantsFragment : BaseFragment(R.layout.view_restaurants_fragment) {

    companion object {
        fun newInstance() = ViewRestaurantsFragment()
    }

    @Inject
    lateinit var repo: RestaurantRepo
    lateinit var viewRestaurantsViewModel: ViewRestaurantsViewModel
    var mRestaurantList=ArrayList<RestaurantItem>()
    lateinit var adapter: ResturantsAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewRestaurantsViewModel = ViewRestaurantsViewModel(repo)

        lifecycleScope.launch {
            viewRestaurantsViewModel.userIntent.send(RestaurantScreenIntent.GetRestaurantList())
        }
        subscribeObservers()
        linearLayoutManager = LinearLayoutManager(activityContext())
        resturantRV.layoutManager = linearLayoutManager
        adapter = ResturantsAdapter(activityContext(), mRestaurantList)
        resturantRV.adapter = adapter
    }

    //As a result, the ViewModel updates the View with new states and is then displayed to the user.
    private fun subscribeObservers() {
        viewRestaurantsViewModel.restaurantsResponse.observe(
            viewLifecycleOwner,
            Observer { restaurantsResponse ->
                when (restaurantsResponse) {
                    is DataState.Success<ArrayList<RestaurantItem>> -> {
                        if (restaurantsResponse.data.size>0/* && mRestaurantList.size<3*/) {
                            noItems.visibility= View.GONE
                            for (restaurant in restaurantsResponse.data) {
                                mRestaurantList.add(restaurant)
                                adapter.notifyItemInserted(mRestaurantList.size)
                            }
                        }
                        if (restaurantsResponse.data.size==0)
                            noItems.visibility= View.VISIBLE
                    }
                    is DataState.Error -> {

                    }
                    is DataState.Loading -> {

                    }
                }
            })
    }

}