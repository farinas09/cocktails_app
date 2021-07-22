package com.farinas.cocktailsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.databinding.FragmentCocktailDetailsBinding

class CocktailDetailsFragment : Fragment() {

    private var _binding: FragmentCocktailDetailsBinding? = null;
    private val binding get() = _binding!!
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
        _binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(cocktail.image).centerCrop().into(binding.cocktailImg)
        binding.cocktailTitle.text = cocktail.name
        binding.cocktailDescription.text = cocktail.description
        binding.cocktailAlcoholic.text = cocktail.hasAlcohol

    }
}