package com.media.shaadoow.foundation.mvi

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import android.text.format.DateUtils
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.shisheo.R
import dagger.hilt.android.internal.managers.ViewComponentManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
open class BaseFragment(layout:Int):Fragment(layout){
    var loadingDialog:Dialog?=null

    fun loading(){
        loadingDialog = Dialog(activityContext(), R.style.Theme_Design_BottomSheetDialog)
        loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog!!.setCancelable(true)
        loadingDialog!!.setContentView(R.layout.loading)
        val window: Window = loadingDialog!!.window!!
        window.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT
        )
        if (!requireActivity().isFinishing) {
            loadingDialog?.show()
        }

    }
    fun stopLoading(){
        if (loadingDialog!=null && !requireActivity().isFinishing) loadingDialog!!.dismiss()
    }

    fun activityContext(): Context {
        val context = context
        return if (context is ViewComponentManager.FragmentContextWrapper) {
            context.baseContext
        } else context!!
    }

    fun showMessage( msg: String) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)
        val text = dialog.findViewById(R.id.text_dialog) as TextView
        text.text = msg
        val dialogButton: Button = dialog.findViewById(R.id.btn_dialog) as Button
        dialogButton.setOnClickListener{ dialog.dismiss() }
        dialog.show()
    }

}
