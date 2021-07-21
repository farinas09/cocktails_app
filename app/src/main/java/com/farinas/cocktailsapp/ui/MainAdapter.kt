package com.farinas.cocktailsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farinas.cocktailsapp.R
import com.farinas.cocktailsapp.base.BaseViewHolder
import com.farinas.cocktailsapp.data.model.Drink
import com.farinas.cocktailsapp.databinding.DrinkRowBinding

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
class MainAdapter(private val context: Context, private val cocktailsList: List<Drink>, private val itemClickListener:OnCocktailClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnCocktailClickListener {
        fun onCocktailClick(drink: Drink)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.drink_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cocktailsList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return cocktailsList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        private val binding = DrinkRowBinding.bind(itemView)
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(binding.cocktailImg)
            binding.textTitle.text = item.name
            binding.textDescription.text = item.description
            itemView.setOnClickListener{itemClickListener.onCocktailClick(item)}
        }

    }
}