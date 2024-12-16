package com.example.bookexplorerapp.network

import com.example.bookexplorerapp.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("subjects/love.json?limit=20")
    suspend fun getBooks(): Response<BookResponse>
}
