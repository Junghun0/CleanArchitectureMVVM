package com.example.cleanarchitecture.presenter.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object MainBindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions().fitCenter())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }
}