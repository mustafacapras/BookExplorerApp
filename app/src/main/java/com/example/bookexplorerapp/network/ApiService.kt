package com.example.bookexplorerapp.network

import com.example.bookexplorerapp.model.BookDetailsResponse
import com.example.bookexplorerapp.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Search books by title
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): Response<BookResponse>

    // Get details of a book by its work ID
    @GET("works/{workId}.json")
    suspend fun getBookDetails(
        @Path("workId") workId: String
    ): Response<BookDetailsResponse>
}
