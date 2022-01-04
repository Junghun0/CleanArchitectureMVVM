package com.example.cleanarchitecture.presenter.info

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.cleanarchitecture.common.StatusBarUtil
import com.example.cleanarchitecture.common.TypeColorUtil
import com.example.cleanarchitecture.data.main.dto.Pokemon
import com.example.cleanarchitecture.data.main.dto.TypeResponse
import com.example.cleanarchitecture.databinding.ActivityInfoBinding
import com.example.cleanarchitecture.domain.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

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
        observeData()
    }

    private fun observeData() {
        infoViewModel.pokemonInfo.observe(this, {
            binding.pokemonInfo = it
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