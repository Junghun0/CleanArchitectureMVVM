package com.example.cleanarchitecture.presenter.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.domain.base.BaseActivity
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.presenter.info.InfoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {

    private val mainViewModel by viewModels<MainViewModel>()
    private val listAdapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            viewModel = mainViewModel
            adapter = listAdapter
        }

        listAdapter.onItemClick = { pokemon, container ->
            Intent(this, InfoActivity::class.java).apply {
                putExtra(POKEMON, pokemon)
            }.run {
                val pair = Pair(container as View, container.transitionName)
                val optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, pair)
                startActivity(this, optionsCompat.toBundle())
            }
        }

        lifecycleScope.launch {
            mainViewModel.pagingPokemonList().collectLatest {
                listAdapter.submitData(it)
            }
        }
    }

    companion object {
        const val POKEMON = "pokemon"
    }
}