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

        val workId = intent.getStringExtra("work_id") ?: return

        val titleTextView: TextView = findViewById(R.id.tvBookDetailsTitle)
        val authorTextView: TextView = findViewById(R.id.tvBookDetailsAuthor)
        val coverImageView: ImageView = findViewById(R.id.ivBookDetailsCover)
        val descriptionTextView: TextView = findViewById(R.id.tvBookDetailsDescription)

        lifecycleScope.launch {
            val response: Response<BookDetailsResponse> = RetrofitClient.getApiService().getBookDetails(workId)
            if (response.isSuccessful) {
                val bookDetails = response.body()
                titleTextView.text = bookDetails?.title ?: "No Title"
                authorTextView.text = "Author: ${bookDetails?.author ?: "Unknown Author"}"
                descriptionTextView.text = bookDetails?.description?.toString() ?: "Description not available"

                Glide.with(this@BookDetailsActivity)
                    .load(bookDetails?.coverUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(coverImageView)
            } else {
                descriptionTextView.text = "Failed to load book details"
            }
        }
    }
}
