package com.example.cleanarchitecture.common

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import android.os.Build
import android.app.Activity

object StatusBarUtil {
    @SuppressLint("ObsoleteSdkInt")
    fun setStatusBarColor(activity: Activity, colorType: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor =
                ContextCompat.getColor(activity, colorType)
        }
    }
}