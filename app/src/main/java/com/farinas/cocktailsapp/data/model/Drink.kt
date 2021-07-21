package com.farinas.cocktailsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Erick Fariñas on 21/07/2021.
 */

@Parcelize
data class Drink(
    val image: String = "",
    val name: String = "",
    val description: String = ""
) : Parcelable
