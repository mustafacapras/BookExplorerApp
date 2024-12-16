package com.example.bookexplorerapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexplorerapp.adapter.BookAdapter

class MainPageActivity : AppCompatActivity() {

    private val viewModel: MainPageViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BookAdapter(emptyList())
        recyclerView.adapter = adapter

        observeViewModel()
        viewModel.fetchBooks()
    }

    private fun observeViewModel() {
        viewModel.bookList.observe(this) { books ->
            adapter = BookAdapter(books)
            recyclerView.adapter = adapter
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }
}
