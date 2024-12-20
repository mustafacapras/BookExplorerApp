package com.example.bookexplorerapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Extract data passed via Intent
        val bookTitle = intent.getStringExtra("bookTitle") ?: "No Title"
        val bookAuthor = intent.getStringExtra("bookAuthor") ?: "Unknown Author"
        val bookCoverUrl = intent.getStringExtra("bookCoverUrl") ?: ""
        val bookDescription = intent.getStringExtra("bookDescription") ?: "Description not available"

        // Find views
        val titleTextView: TextView = findViewById(R.id.tvBookDetailsTitle)
        val authorTextView: TextView = findViewById(R.id.tvBookDetailsAuthor)
        val coverImageView: ImageView = findViewById(R.id.ivBookDetailsCover)
        val descriptionTextView: TextView = findViewById(R.id.tvBookDetailsDescription)

        // Set data to views
        titleTextView.text = bookTitle
        authorTextView.text = "Author: $bookAuthor"
        descriptionTextView.text = bookDescription

        // Load cover image using Glide
        Glide.with(this)
            .load(bookCoverUrl)
            .placeholder(R.drawable.placeholder) // Placeholder image
            .into(coverImageView)
    }
}
