package com.example.cleanarchitecture.presenter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = mainViewModel

        mainViewModel.fetchPokemonList(20, 10)
        mainViewModel.fetchPokemonInfo("butterfree")
        observeData()
    }

    private fun observeData() {
        mainViewModel.pokemonList.observe(this, Observer {
            Log.d("jhjh"," list- >?> ${it}")
        })

        mainViewModel.pokemonInfo.observe(this, {
            Log.d("jhjh"," info ->>> $it")
        })
    }
}