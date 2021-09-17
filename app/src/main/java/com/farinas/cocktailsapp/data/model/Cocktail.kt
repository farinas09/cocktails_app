package com.farinas.cocktailsapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Erick Fariñas on 21/07/2021.
 */

@Parcelize
data class Cocktail(
    @SerializedName("idDrink")
    val cocktailId: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non Alcoholic"
) : Parcelable

data class CocktailsList(
    @SerializedName("drinks")
    val cocktailList: List<Cocktail>
)

@Entity(tableName = "cocktailTable")
data class CocktailEntity(
    @PrimaryKey
    val cocktailId: String,
    @ColumnInfo(name = "cocktail_image")
    val image: String = "",
    @ColumnInfo(name = "cocktail_nombre")
    val name: String = "",
    @ColumnInfo(name = "cocktail_description")
    val description: String = "",
    @ColumnInfo(name = "cocktail_has_alcohol")
    val hasAlcohol: String = "Non_Alcoholic"
)

fun Cocktail.asCocktailEntity(): CocktailEntity =
    CocktailEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)