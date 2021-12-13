package com.example.cleanarchitecture.presenter.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.common.BaseActivity
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = mainViewModel
        adapter = MainAdapter()
        binding.mainRecyclerView.adapter = adapter
        observeData()
    }

    private fun observeData() {
        mainViewModel.pokemonList.observe(this, Observer {
            adapter.submitList(it)
            Log.d("jhjh", " list- >?> ${it}")
        })

        mainViewModel.pokemonInfo.observe(this, {
            Log.d("jhjh", " info ->>> $it")
        })
    }
}