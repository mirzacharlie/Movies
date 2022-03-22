package ru.mirzacharlie.movies.repositories

import ru.mirzacharlie.movies.data.PreferencesManager
import ru.mirzacharlie.movies.domain.models.Page
import ru.mirzacharlie.movies.domain.repository.PageRepository

class PageRepositoryImpl(private val preferencesManager: PreferencesManager) : PageRepository {

    override fun setLastLoadedPageNumber(page: Page) {
        preferencesManager.setCachedPages(page.value)
    }

    override fun getLastLoadedPageNumber(): Page =
        Page(preferencesManager.getCachedPages())
}