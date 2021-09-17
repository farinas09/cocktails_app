package com.farinas.cocktailsapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.farinas.cocktailsapp.data.local.AppDatabase
import com.farinas.cocktailsapp.R
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.databinding.FragmentMainBinding
import com.farinas.cocktailsapp.domain.CocktailRepositoryImpl
import com.farinas.cocktailsapp.presentation.MainViewModel
import com.farinas.cocktailsapp.ui.viewmodel.VMFactory
import com.farinas.cocktailsapp.vo.Resource

class MainFragment : Fragment(), MainAdapter.OnCocktailClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(CocktailRepositoryImpl(DataSource(
        AppDatabase.getDatabase(requireActivity().applicationContext)))) }
    private var _binding: FragmentMainBinding? = null;
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvCocktails.adapter = MainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "An error occurred while fetching the data ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.setCocktail(query)
                } else {
                    viewModel.setCocktail("")
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun setupRecyclerView() {
        binding.rvCocktails.layoutManager = LinearLayoutManager(requireContext())
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