package com.example.bookexplorerapp.network

import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

object RetrofitClient {

    private const val BASE_URL = "https://openlibrary.org/"

    // Custom deserializer to handle String and Object types for "description"
    class DescriptionDeserializer : JsonDeserializer<String> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): String {
            return if (json != null && json.isJsonObject) {
                json.asJsonObject["value"]?.asString ?: "No description available"
            } else {
                json?.asString ?: "No description available"
            }
        }
    }

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(String::class.java, DescriptionDeserializer())
        .create()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}
