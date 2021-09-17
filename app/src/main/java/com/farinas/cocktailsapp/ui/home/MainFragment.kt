package com.farinas.cocktailsapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.farinas.cocktailsapp.R
import com.farinas.cocktailsapp.core.Resource
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.databinding.FragmentMainBinding
import com.farinas.cocktailsapp.presentation.MainViewModel
import com.farinas.cocktailsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.OnCocktailClickListener {

    private val viewModel by activityViewModels<MainViewModel>()
    private var _binding: FragmentMainBinding? = null;
    private val binding get() = _binding!!
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainAdapter = MainAdapter(requireContext(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpSearchView()
        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.fetchCocktailsList.observe(viewLifecycleOwner, Observer { result ->
            binding.progressBar.showIf { result is Resource.Loading }
            when (result) {
                is Resource.Loading -> {
                    binding.emptyContainer.root.hide()
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.rvCocktails.hide()
                        binding.emptyContainer.root.show()
                        return@Observer
                    }
                    binding.rvCocktails.show()
                    mainAdapter.setCocktailList(result.data)
                    binding.emptyContainer.root.hide()
                }
                is Resource.Failure -> {
                    showToast("Ocurrió un error al traer los datos ${result.exception}")
                }
            }
        })
    }

    private fun setUpSearchView() {
        binding.searchView.onQueryTextChanged {
            viewModel.setCocktail(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvCocktails.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCocktails.adapter = mainAdapter
    }

    override fun onCocktailClick(cocktail: Cocktail) {
        val bundle = Bundle()
        bundle.putParcelable("cocktail", cocktail)
        findNavController().navigate(R.id.action_mainFragment_to_cocktailDetailsFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favs -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
                false
            }
            else -> false
        }
    }

}