package com.example.exlivedata.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.exlivedata.R
import com.example.exlivedata.databinding.ActivityMainBinding
import com.example.exlivedata.ui.view.fragment.ListQuoteFragment
import com.example.exlivedata.ui.viewmodel.QuoteViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var quoteViewModel: QuoteViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNavController()

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, ListQuoteFragment()).commit()

        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        binding.lifecycleOwner = this
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//        setupWithNavController(binding.bottomNavigationView, navController)
    }
}