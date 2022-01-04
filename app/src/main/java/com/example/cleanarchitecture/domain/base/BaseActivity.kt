package com.example.cleanarchitecture.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.common.StatusBarUtil
import com.example.cleanarchitecture.common.TypeColorUtil

abstract class BaseActivity<B: ViewBinding>(
    val bindingFactory: (LayoutInflater) -> B
): AppCompatActivity() {

    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        StatusBarUtil.setStatusBarColor(this, TypeColorUtil.getTypeColor(getString(R.string.default_value)))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}