package ru.mirzacharlie.movies.domain.repository

import ru.mirzacharlie.movies.domain.models.Page

interface PageRepository {

    fun setLastLoadedPageNumber(page: Page)

    fun getLastLoadedPageNumber(): Page
}