package com.example.bookexplorerapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.bookexplorerapp.model.BookDetailsResponse
import com.example.bookexplorerapp.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Response

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val bookTitle = intent.getStringExtra("bookTitle") ?: "No Title"
        val bookAuthor = intent.getStringExtra("bookAuthor") ?: "Unknown Author"
        val bookCoverUrl = intent.getStringExtra("bookCoverUrl") ?: ""
        val workId = intent.getStringExtra("workId")

        val titleTextView: TextView = findViewById(R.id.tvBookDetailsTitle)
        val authorTextView: TextView = findViewById(R.id.tvBookDetailsAuthor)
        val coverImageView: ImageView = findViewById(R.id.ivBookDetailsCover)
        val descriptionTextView: TextView = findViewById(R.id.tvBookDetailsDescription)

        titleTextView.text = bookTitle
        authorTextView.text = "Author: $bookAuthor"

        Glide.with(this)
            .load(bookCoverUrl)
            .placeholder(R.drawable.placeholder)
            .into(coverImageView)

        if (workId != null) {
            fetchBookDescription(workId, descriptionTextView)
        } else {
            descriptionTextView.text = "Description not available"
        }
    }

    private fun fetchBookDescription(workId: String, descriptionTextView: TextView) {
        lifecycleScope.launch {
            try {
                val response: Response<BookDetailsResponse> =
                    RetrofitClient.getApiService().getBookDetails(workId.removePrefix("/works/"))
                if (response.isSuccessful) {
                    val description = when (val desc = response.body()?.description) {
                        is String -> desc
                        is Map<*, *> -> desc["value"] as? String ?: "No description available"
                        else -> "No description available"
                    }
                    descriptionTextView.text = description
                } else {
                    descriptionTextView.text = "Failed to load description"
                }
            } catch (e: Exception) {
                descriptionTextView.text = "Error: ${e.message}"
            }
        }
    }
}
