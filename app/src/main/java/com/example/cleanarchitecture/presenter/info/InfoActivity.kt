package com.example.cleanarchitecture.presenter.info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.cleanarchitecture.common.StatusBarUtil
import com.example.cleanarchitecture.common.TypeColorUtil
import com.example.cleanarchitecture.data.main.dto.Pokemon
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
        supportPostponeEnterTransition()
        StatusBarUtil.setStatusBarColor(this, StatusBarUtil.StatusBarColorType.BLACK_STATUS_BAR)

        val pokemon = intent.getParcelableExtra<Pokemon>("pokemon")

        pokemon?.let {
            binding.pokemon = it
            infoViewModel.fetchPokemonInfo(it.name)
        }

        observeData()
    }

    private fun observeData() {
        infoViewModel.pokemonInfo.observe(this, {
            binding.pokemonInfo = it
            binding.infoBackground.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    TypeColorUtil.getTypeColor(it.types[0].type.name)
                )
            )
            supportStartPostponedEnterTransition()
        })
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}