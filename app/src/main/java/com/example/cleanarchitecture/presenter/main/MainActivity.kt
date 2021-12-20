package com.example.cleanarchitecture.presenter.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.common.StatusBarUtil
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
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBarColor(this, StatusBarUtil.StatusBarColorType.BLACK_STATUS_BAR)

        binding.viewModel = mainViewModel
        adapter = MainAdapter()
        adapter.onItemClick = { pokemon, container ->
            Intent(this, InfoActivity::class.java).apply {
                putExtra(POKEMON, pokemon)
            }.run {
                val pair = Pair(container as View, container.transitionName)
                val optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, pair)
                startActivity(this, optionsCompat.toBundle())
            }
        }
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
    }

    companion object {
        const val POKEMON = "pokemon"
    }
}