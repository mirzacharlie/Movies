package ru.mirzacharlie.movies.ui.base

import androidx.appcompat.app.AppCompatActivity
import ru.mirzacharlie.movies.di.AppInjector

abstract class BaseActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.unholdComponent(this)
    }
}
