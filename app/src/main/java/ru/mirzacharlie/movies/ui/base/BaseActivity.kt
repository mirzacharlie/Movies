package ru.mirzacharlie.movies.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity
import ru.mirzacharlie.movies.di.AppInjector

abstract class BaseActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.destroyComponent(this)
    }
}
