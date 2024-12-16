package com.example.bookexplorerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Get book title from Intent
        val bookTitle = intent.getStringExtra("bookTitle")

        // Set title to TextView
        val titleTextView: TextView = findViewById(R.id.tvBookDetailsTitle)
        titleTextView.text = bookTitle
    }
}
