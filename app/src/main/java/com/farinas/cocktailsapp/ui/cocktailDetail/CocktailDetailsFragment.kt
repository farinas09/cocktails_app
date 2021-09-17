package com.farinas.cocktailsapp.ui.cocktailDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.farinas.cocktailsapp.R
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.databinding.FragmentCocktailDetailsBinding
import com.farinas.cocktailsapp.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CocktailDetailsFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: FragmentCocktailDetailsBinding
    private lateinit var cocktail: Cocktail
    private var isCocktailFavorite: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            CocktailDetailsFragmentArgs.fromBundle(it).also { args ->
            cocktail = args.cocktail
        }
        }
        Log.d("DETALLES", cocktail.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(cocktail.image).centerCrop().into(binding.cocktailImg)
        binding.cocktailTitle.text = cocktail.name
        binding.cocktailDescription.text = cocktail.description
        binding.cocktailAlcoholic.text = cocktail.hasAlcohol

        fun updateButtonIcon() {
            val isCocktailFavorited = isCocktailFavorite ?: return

            binding.favoriteButton.setImageResource(
                when {
                    isCocktailFavorited -> R.drawable.ic_baseline_delete_24
                    else -> R.drawable.ic_favorite
                }
            )
        }

        binding.favoriteButton.setOnClickListener {
            val isCocktailFavorite = isCocktailFavorite ?: return@setOnClickListener

            viewModel.saveOrDeleteFavoriteCocktail(cocktail)
            this.isCocktailFavorite = !isCocktailFavorite
            updateButtonIcon()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            isCocktailFavorite = viewModel.isCocktailFavorite(cocktail)
            updateButtonIcon()
        }

    }
}