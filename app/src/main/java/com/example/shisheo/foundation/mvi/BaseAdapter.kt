package com.media.shaadoow.foundation.mvi

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.internal.managers.ViewComponentManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
abstract class BaseAdapter(var context1: Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun activityContext(context1: Context): Context? {
        val context = context1
        return if (context is ViewComponentManager.FragmentContextWrapper) {
            context.baseContext
        } else context
    }
}