package com.farinas.cocktailsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.farinas.cocktailsapp.AppDatabase
import com.farinas.cocktailsapp.data.DataSource
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.databinding.FragmentCocktailDetailsBinding
import com.farinas.cocktailsapp.domain.RepositoryImpl
import com.farinas.cocktailsapp.ui.viewmodel.MainViewModel
import com.farinas.cocktailsapp.ui.viewmodel.VMFactory

class CocktailDetailsFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepositoryImpl(DataSource(
        AppDatabase.getDatabase(requireActivity().applicationContext)))) }
    private lateinit var binding: FragmentCocktailDetailsBinding
    private lateinit var cocktail: Cocktail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let { cocktail = it.getParcelable("cocktail")!! }
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

        binding.favoriteButton.setOnClickListener {
            viewModel.saveFavorite(
                CocktailEntity(
                    cocktail.cocktailId,
                    cocktail.image,
                    cocktail.name,
                    cocktail.description,
                    cocktail.hasAlcohol
                )
            )
            Toast.makeText(requireContext(), "Se agregó a favoritos", Toast.LENGTH_SHORT).show()
        }

    }
}