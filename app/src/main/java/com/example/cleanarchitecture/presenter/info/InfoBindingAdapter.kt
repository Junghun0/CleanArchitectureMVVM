package com.example.cleanarchitecture.presenter.info

import android.widget.TextView
import androidx.databinding.BindingAdapter

object InfoBindingAdapter {

    @BindingAdapter("setName")
    @JvmStatic
    fun bindText(textView: TextView, name: String) {
        val builder = StringBuilder()
        val temp = name.substring(1, name.length)
        val upperName = name[0].uppercase()
        textView.text = builder.append(upperName).append(temp).toString()
    }

    @BindingAdapter("setId")
    @JvmStatic
    fun bindIdText(textView: TextView, id: Int) {
        val builder = StringBuilder()
        textView.text = builder.append("# ").append(id).toString()
    }
}