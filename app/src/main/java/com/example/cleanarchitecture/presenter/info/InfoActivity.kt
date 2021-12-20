package com.example.cleanarchitecture.presenter.info

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.activity.viewModels
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

        intent.getParcelableExtra<Pokemon>(POKEMON)?.let {
            binding.pokemon = it
            infoViewModel.fetchPokemonInfo(it.name)
        }

        observeData()
    }

    private fun observeData() {
        infoViewModel.pokemonInfo.observe(this, {
            binding.pokemonInfo = it
            val colorId = TypeColorUtil.getTypeColor(it.types[0].type.name)

            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                TypeColorUtil.getGradientColor(it.types, this)
            )
            gradientDrawable.cornerRadius = 0f
            binding.infoBackground.background = gradientDrawable

            StatusBarUtil.setStatusBarColor(this, colorId)
            supportStartPostponedEnterTransition()
        })
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

    companion object {
        const val POKEMON = "pokemon"
    }
}