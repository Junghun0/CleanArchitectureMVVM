package com.example.cleanarchitecture.data.main.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

@Keep
@Serializable
data class PokemonInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("id") val experience: Int,
)