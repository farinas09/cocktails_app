package com.farinas.cocktailsapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int)
}