package com.example.gamestores.ui.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import coil.ImageLoader
import coil.load
import com.example.gamestores.R
import com.example.gamestores.databinding.ActivityMainBinding
import com.example.gamestores.ui.viewmodel.GameViewModel
import com.google.android.material.search.SearchBar


public class MainActivity : AppCompatActivity() {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNavController()
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        binding.lifecycleOwner = this
        binding.url = "https://www.freetogame.com/g/452/thumbnail.jpg"
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
    companion object {
        @JvmStatic
        @BindingAdapter("app:avatarUrl")
        fun loadAvatarUrl(imageView: ImageView, avatarUrl: String?) {
            avatarUrl.let { url ->
                val imageLoader = ImageLoader.Builder(imageView.context)
                    .build()
                imageView.load(url, imageLoader)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}