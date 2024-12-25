package com.example.bookexplorerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexplorerapp.adapter.BookAdapter
import com.example.bookexplorerapp.model.Book
import com.example.bookexplorerapp.network.RetrofitClient

class MainPageActivity : AppCompatActivity() {

    private lateinit var viewModel: MainPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val apiService = RetrofitClient.getApiService()
        val factory = MainPageViewModelFactory(apiService)
        viewModel = ViewModelProvider(this, factory)[MainPageViewModel::class.java]

        val searchEditText: EditText = findViewById(R.id.etSearch)
        val recyclerView: RecyclerView = findViewById(R.id.rvBooks)

        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.bookList.observe(this) { books ->
            Log.d("MainPageActivity", "Books Updated: $books")
            recyclerView.adapter = BookAdapter(books,
                onFavoriteClick = { book ->
                    Log.d("MainPageActivity", "Favorite clicked for: ${book.title}")
                },
                onBookClick = { book ->
                    navigateToBookDetails(book)
                }
            )
        }

        viewModel.errorMessage.observe(this) { error ->
            if (!error.isNullOrEmpty()) {
                Log.e("MainPageActivity", "Error: $error")
            }
        }

        searchEditText.setOnEditorActionListener { _, _, _ ->
            val query = searchEditText.text.toString()
            viewModel.searchBooks(query)
            true
        }
    }

    private fun navigateToBookDetails(book: Book) {
        val intent = Intent(this, BookDetailsActivity::class.java)
        intent.putExtra("work_id", book.id) // Kitap ID'sini gönder
        startActivity(intent)
    }
}
