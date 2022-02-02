package ru.mirzacharlie.movies.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.mirzacharlie.movies.R
import ru.mirzacharlie.movies.di.ViewModelInjection
import ru.mirzacharlie.movies.ui.base.BaseActivity
import ru.mirzacharlie.movies.ui.screens.main.MainFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: MainVM

    private lateinit var navHost: NavHostFragment
    private val navController: NavController
        get() = navHost.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHost = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        navController.navigate(R.id.mainFragment)

    }
}
