package com.example.bookexplorerapp

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexplorerapp.adapter.BookAdapter

class MainPageActivity : AppCompatActivity() {

    private val viewModel: MainPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val searchEditText: EditText = findViewById(R.id.etSearch)
        val recyclerView: RecyclerView = findViewById(R.id.rvBooks)

        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.bookList.observe(this) { books ->
            recyclerView.adapter = BookAdapter(books)
        }

        searchEditText.setOnEditorActionListener { _, _, _ ->
            val query = searchEditText.text.toString()
            viewModel.searchBooks(query)
            true
        }
    }
}
