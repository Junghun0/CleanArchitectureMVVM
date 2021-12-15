package com.example.cleanarchitecture.presenter.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.domain.base.BaseActivity
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            mainViewModel.pagingPokemonList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun observeData() {
        mainViewModel.pokemonList.observe(this, Observer {
            Log.d("jhjh", " list- >?> ${it}")
        })

        mainViewModel.pokemonInfo.observe(this, {
            Log.d("jhjh", " info ->>> $it")
        })
    }
}