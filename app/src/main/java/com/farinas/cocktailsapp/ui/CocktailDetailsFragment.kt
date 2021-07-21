package com.farinas.cocktailsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farinas.cocktailsapp.R
import com.farinas.cocktailsapp.data.model.Drink
import com.farinas.cocktailsapp.databinding.FragmentCocktailDetailsBinding
import com.farinas.cocktailsapp.databinding.FragmentMainBinding

class CocktailDetailsFragment : Fragment() {

    private var _binding: FragmentCocktailDetailsBinding? = null;
    private val binding get() = _binding!!
    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let { drink = it.getParcelable("drink")!! }
        Log.d("DETALLES", drink.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}