package com.example.cleanarchitecture.presenter.info

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.cleanarchitecture.common.StatusBarUtil
import com.example.cleanarchitecture.databinding.ActivityInfoBinding
import com.example.cleanarchitecture.domain.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoActivity : BaseActivity<ActivityInfoBinding>({
    ActivityInfoBinding.inflate(it)
}) {
    private val infoViewModel by viewModels<InfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBarColor(this, StatusBarUtil.StatusBarColorType.BLACK_STATUS_BAR)

        val pokemonName = intent.getStringExtra("name")
        val imageUrl = intent.getStringExtra("url")

        pokemonName?.let {
            infoViewModel.fetchPokemonInfo(pokemonName)
        }

        observeData()
    }

    private fun observeData() {
        infoViewModel.pokemonInfo.observe(this, {
            Log.e("jhjh"," info data -> $it")
        })
    }
}