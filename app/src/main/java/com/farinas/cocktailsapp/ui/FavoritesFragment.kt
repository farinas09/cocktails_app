package com.farinas.cocktailsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.farinas.cocktailsapp.AppDatabase
import com.farinas.cocktailsapp.data.DataSource
import com.farinas.cocktailsapp.databinding.FragmentFavoritesBinding
import com.farinas.cocktailsapp.domain.RepositoryImpl
import com.farinas.cocktailsapp.ui.viewmodel.MainViewModel
import com.farinas.cocktailsapp.ui.viewmodel.VMFactory
import com.farinas.cocktailsapp.vo.Resource


class FavoritesFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepositoryImpl(
                DataSource(
                    AppDatabase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }

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
        viewModel.getFavoritesCocktails().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Log.d("FAVORITOS", "${result.data}")

                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "An error occurred ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


}