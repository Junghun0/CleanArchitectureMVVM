package com.example.cleanarchitecture.common

import android.app.Activity
import androidx.core.content.ContextCompat
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.main.dto.TypeResponse

object TypeColorUtil {
    fun getTypeColor(type: String): Int {
        return when (type) {
            "fighting" -> R.color.fighting
            "flying" -> R.color.flying
            "poison" -> R.color.poison
            "ground" -> R.color.ground
            "rock" -> R.color.rock
            "bug" -> R.color.bug
            "ghost" -> R.color.ghost
            "steel" -> R.color.steel
            "fire" -> R.color.fire
            "water" -> R.color.water
            "grass" -> R.color.grass
            "electric" -> R.color.electric
            "psychic" -> R.color.psychic
            "ice" -> R.color.ice
            "dragon" -> R.color.dragon
            "fairy" -> R.color.fairy
            "dark" -> R.color.dark
            "default" -> R.color.purple_200
            "white" -> R.color.white
            else -> R.color.gray_21
        }
    }

    fun getGradientColor(types: List<TypeResponse>, context: Activity): IntArray {
        val colorArray = IntArray(2)
        if (types.size == 2) {
            colorArray[0] =
                ContextCompat.getColor(
                    context,
                    getTypeColor(types[0].type.name)
                )
            colorArray[1] =
                ContextCompat.getColor(
                    context,
                    getTypeColor(types[1].type.name)
                )
        } else if (types.size == 1) {
            colorArray[0] =
                ContextCompat.getColor(
                    context,
                    getTypeColor(types[0].type.name)
                )
            colorArray[1] =
                ContextCompat.getColor(
                    context,
                    getTypeColor("white")
                )
        }
        return colorArray
    }
}