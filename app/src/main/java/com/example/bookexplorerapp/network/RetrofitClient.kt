package com.example.bookexplorerapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://openlibrary.org/"

    // Create Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide ApiService instance
    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}
