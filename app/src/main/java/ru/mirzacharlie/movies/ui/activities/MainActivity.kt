package ru.mirzacharlie.movies.ui.activities

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.mirzacharlie.movies.R
import ru.mirzacharlie.movies.databinding.ActivityMainBinding
import ru.mirzacharlie.movies.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

//    @Inject
//    lateinit var viewModel: MainVM

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private lateinit var navHost: NavHostFragment
    private val navController: NavController
        get() = navHost.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHost = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        binding.bottomNavBar.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
