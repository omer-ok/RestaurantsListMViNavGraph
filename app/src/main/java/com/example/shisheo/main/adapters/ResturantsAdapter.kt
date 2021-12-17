package com.example.shisheo.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shisheo.R
import com.example.shisheo.main.model.RestaurantItem
import com.media.shaadoow.foundation.mvi.BaseAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.resturant_item.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class ResturantsAdapter(var context: Context, var  restaurant: ArrayList<RestaurantItem>
) : BaseAdapter(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resturant_item, parent, false)
        return ViewHolder(view)
    }

    var articleImage:String=""
    override fun onBindViewHolder(holder1: RecyclerView.ViewHolder, position: Int) {
        val holder=holder1 as ViewHolder
        if(restaurant.size>0){
            articleImage =restaurant[position].image_url
            Glide.with(context)
                .load(articleImage)
                .fitCenter()
                .into(holder.mDishImage)
        }

        holder.mResturantName.text=restaurant[position].name
        holder.mDescription.text=restaurant[position].description
        holder.mOffers.text=restaurant[position].offer
        holder.itemView.setOnClickListener {
            if(holder.mRatingView.visibility == View.VISIBLE){
                holder.mRatingView.visibility = View.GONE
            }else{
                holder.mRatingView.visibility = View.VISIBLE
            }
        }

    }

    override fun getItemCount(): Int {
        return restaurant.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var mDishImage: ImageView = itemView.foodImage
        internal var mResturantName: TextView = itemView.resturantName
        internal var mDescription: TextView = itemView.foodDescription
        internal var mOffers: TextView = itemView.offers
        internal var mRatingView: View = itemView.ratingView
    }

}