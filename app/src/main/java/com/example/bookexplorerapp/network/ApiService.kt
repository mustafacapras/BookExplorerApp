package com.example.bookexplorerapp.network

import com.example.bookexplorerapp.model.BookResponse
import retrofit2.Call
import retrofit2.http.GET

// Define API for fetching book data
interface ApiService {
    @GET("https://openlibrary.org/subjects/love.json?limit=20")
    fun getBooks(): Call<BookResponse>
}
