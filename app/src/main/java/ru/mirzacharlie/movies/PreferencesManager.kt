package ru.mirzacharlie.movies

import android.content.SharedPreferences

class PreferencesManager(private val sharedPreferences: SharedPreferences) {

    private val SAVED_PAGES = "saved pages"

    fun getCashedPages() =
        sharedPreferences.getInt(SAVED_PAGES, 0)

    fun setCashedPages(pages: Int) {
        sharedPreferences.edit().putInt(SAVED_PAGES, pages).apply()
    }
}
