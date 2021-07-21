package com.farinas.cocktailsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.farinas.cocktailsapp.data.DataSource
import com.farinas.cocktailsapp.domain.RepositoryImpl
import com.farinas.cocktailsapp.ui.viewmodel.MainViewModel
import com.farinas.cocktailsapp.ui.viewmodel.VMFactory

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController

    private val viewModel by viewModels<MainViewModel>{VMFactory(RepositoryImpl(DataSource()))}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}