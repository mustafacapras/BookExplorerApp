package com.example.bookexplorerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexplorerapp.adapter.BookAdapter
import com.example.bookexplorerapp.model.Book
import com.example.bookexplorerapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPageActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchBooks()
    }

    private fun fetchBooks() {
        RetrofitClient.apiService.getBooks().enqueue(object : Callback<com.example.bookexplorerapp.model.BookResponse> {
            override fun onResponse(
                call: Call<com.example.bookexplorerapp.model.BookResponse>,
                response: Response<com.example.bookexplorerapp.model.BookResponse>
            ) {
                if (response.isSuccessful) {
                    val books = response.body()?.works ?: emptyList()
                    adapter = BookAdapter(books)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<com.example.bookexplorerapp.model.BookResponse>, t: Throwable) {
                Toast.makeText(this@MainPageActivity, "Failed to load books", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
