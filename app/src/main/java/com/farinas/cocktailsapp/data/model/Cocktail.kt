package com.farinas.cocktailsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Erick Fariñas on 21/07/2021.
 */

@Parcelize
data class Cocktail(
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non Alcoholic"
) : Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val cocktailList: List<Cocktail>
)