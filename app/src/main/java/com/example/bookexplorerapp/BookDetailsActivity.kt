package com.example.bookexplorerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Get data passed via Intent
        val title = intent.getStringExtra("BOOK_TITLE")
        val description = intent.getStringExtra("BOOK_DESCRIPTION")

        // Bind data to the TextViews
        val titleTextView = findViewById<TextView>(R.id.tvBookDetailsTitle)
        val descriptionTextView = findViewById<TextView>(R.id.tvBookDetailsDescription)

        titleTextView.text = title
        descriptionTextView.text = description ?: "No description available"
    }
}
