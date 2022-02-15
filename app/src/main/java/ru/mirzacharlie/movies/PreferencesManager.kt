package ru.mirzacharlie.movies

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val SAVED_PAGES = "saved pages"

    fun getCashedPages() =
        sharedPreferences.getInt(SAVED_PAGES, 0)

    fun setCashedPages(pages: Int) {
        sharedPreferences.edit().putInt(SAVED_PAGES, pages).apply()
    }
}