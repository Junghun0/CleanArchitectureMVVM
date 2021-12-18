package com.example.cleanarchitecture.common

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import android.os.Build
import android.app.Activity
import android.content.Context
import com.example.cleanarchitecture.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

object StatusBarUtil {
    enum class StatusBarColorType(val backgroundColorId: Int) {
        BLACK_STATUS_BAR(R.color.black), GREY_STATUS_BAR(R.color.design_default_color_background), BLUE_STATUS_BAR(R.color.cardview_shadow_end_color);
    }

    @SuppressLint("ObsoleteSdkInt")
    fun setStatusBarColor(activity: Activity, colorType: StatusBarColorType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor =
                ContextCompat.getColor(activity, colorType.backgroundColorId)
        }
    }
}