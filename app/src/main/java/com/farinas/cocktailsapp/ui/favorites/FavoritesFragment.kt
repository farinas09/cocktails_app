package com.farinas.cocktailsapp.ui.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.farinas.cocktailsapp.data.local.AppDatabase
import com.farinas.cocktailsapp.R
import com.farinas.cocktailsapp.core.Resource
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.databinding.FragmentFavoritesBinding
import com.farinas.cocktailsapp.domain.CocktailRepositoryImpl
import com.farinas.cocktailsapp.presentation.MainViewModel
import com.farinas.cocktailsapp.ui.viewmodel.VMFactory
import com.farinas.cocktailsapp.utils.showToast
import com.farinas.cocktailsapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesAdapter.OnCocktailClickListener {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var favoritesAdapter: FavoritesAdapter
    private lateinit var binding: FragmentFavoritesBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()

    }

    private fun setupObserver(){
        viewModel.getFavoritesCocktails().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.emptyContainer.root.show()
                        return@Observer
                    }
                    favoritesAdapter.setCocktailList(result.data)
                }
                is Resource.Failure -> {
                    showToast("An error occurred ${result.exception}")
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvFavsCocktails.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavsCocktails.adapter = favoritesAdapter
    }

    override fun onCocktailClick(cocktail: Cocktail, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("cocktail", cocktail)
        findNavController().navigate(FavoritesFragmentDirections.actionFavoritesFragmentToCocktailDetailsFragment(cocktail))
    }


}