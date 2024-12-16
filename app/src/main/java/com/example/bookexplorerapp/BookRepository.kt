package com.example.bookexplorerapp

import com.example.bookexplorerapp.model.BookResponse
import com.example.bookexplorerapp.network.ApiService
import retrofit2.Call

class BookRepository(private val apiService: ApiService) {
    suspend fun getBooks(): Call<BookResponse> {
        return apiService.getBooks()
    }
}
