package com.farinas.cocktailsapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farinas.cocktailsapp.R
import com.farinas.cocktailsapp.core.BaseViewHolder
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.databinding.DrinkRowBinding

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
class MainAdapter(private val context: Context, private val itemClickListener: OnCocktailClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var cocktailList = listOf<Cocktail>()

    interface OnCocktailClickListener {
        fun onCocktailClick(cocktail: Cocktail)
    }

    fun setCocktailList(cocktailList: List<Cocktail>) {
        this.cocktailList = cocktailList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.drink_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cocktailList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return cocktailList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Cocktail>(itemView) {
        private val binding = DrinkRowBinding.bind(itemView)
        override fun bind(item: Cocktail, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(binding.cocktailImg)
            binding.textTitle.text = item.name
            binding.textDescription.text = item.description
            itemView.setOnClickListener{itemClickListener.onCocktailClick(item)}
        }

    }
}