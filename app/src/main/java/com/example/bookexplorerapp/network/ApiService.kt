package com.example.bookexplorerapp.network

import com.example.bookexplorerapp.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // Search books by title
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String, // Search query
        @Query("limit") limit: Int = 20 // Limit results to 20
    ): Response<BookResponse>
}
