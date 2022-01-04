package com.example.cleanarchitecture.data.main.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Serializable
@Parcelize
data class PokemonInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val experience: Int,
    @SerializedName("types") val types: List<TypeResponse>
) : Parcelable

@Parcelize
data class TypeResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: Type
) : Parcelable

@Parcelize
data class Type(
    @SerializedName("name") val name: String
) : Parcelable