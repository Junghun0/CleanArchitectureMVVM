package com.example.cleanarchitecture.presenter.info

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.common.StatusBarUtil
import com.example.cleanarchitecture.common.TypeColorUtil
import com.example.cleanarchitecture.data.main.dto.Pokemon
import com.example.cleanarchitecture.data.main.dto.TypeResponse
import com.example.cleanarchitecture.databinding.ActivityInfoBinding
import com.example.cleanarchitecture.domain.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import android.view.animation.LinearInterpolator

import android.animation.ObjectAnimator




@AndroidEntryPoint
class InfoActivity : BaseActivity<ActivityInfoBinding>({
    ActivityInfoBinding.inflate(it)
}) {
    private val infoViewModel by viewModels<InfoViewModel>()
    private val exp by lazy {
        (1..100).random().toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        intent.getParcelableExtra<Pokemon>(POKEMON)?.let {
            binding.pokemon = it
            infoViewModel.fetchPokemonInfo(it.name)
        }

        binding.exp = exp
        val progressAnimator = ObjectAnimator.ofInt(binding.progressExp, "progress", 0, exp.toInt())
        progressAnimator.duration = 1500
        progressAnimator.interpolator = LinearInterpolator()
        progressAnimator.start()
        observeData()
    }

    private fun observeData() {
        infoViewModel.pokemonInfo.observe(this, {
            binding.pokemonInfo = it
            //PokemonInfo(id=4, name=charmander, height=6, weight=85, experience=62, types=[TypeResponse(slot=1, type=Type(name=fire))])
            setChipData(it.types)
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

    private fun setChipData(types: List<TypeResponse>) {
        if (types.size == 2) {
            binding.type1.text = types[0].type.name
            binding.type1.setChipBackgroundColorResource(TypeColorUtil.getTypeColor(types[0].type.name))

            binding.type2.text = types[1].type.name
            binding.type2.setChipBackgroundColorResource(TypeColorUtil.getTypeColor(types[1].type.name))
        } else if (types.size == 1) {
            binding.type1.text = types[0].type.name
            binding.type1.setChipBackgroundColorResource(TypeColorUtil.getTypeColor(types[0].type.name))

            binding.type2.visibility = View.GONE
        }
    }

    companion object {
        const val POKEMON = "pokemon"
    }
}