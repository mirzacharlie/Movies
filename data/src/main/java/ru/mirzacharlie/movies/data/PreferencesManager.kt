package ru.mirzacharlie.movies.data

import android.content.SharedPreferences

class PreferencesManager(private val sharedPreferences: SharedPreferences) {

    private val SAVED_PAGES = "saved pages"

    fun getCachedPages() =
        sharedPreferences.getInt(SAVED_PAGES, 0)

    fun setCachedPages(pages: Int) {
        sharedPreferences.edit().putInt(SAVED_PAGES, pages).apply()
    }
}
