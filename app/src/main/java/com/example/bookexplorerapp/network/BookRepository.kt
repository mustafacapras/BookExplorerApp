package com.example.bookexplorerapp.network

import com.example.bookexplorerapp.model.BookResponse
import retrofit2.Response

class BookRepository(private val apiService: ApiService) {
    suspend fun searchBooks(query: String): Response<BookResponse> {
        return apiService.searchBooks(query)
    }
}
