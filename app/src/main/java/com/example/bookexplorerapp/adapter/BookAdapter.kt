package com.example.bookexplorerapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookexplorerapp.BookDetailsActivity
import com.example.bookexplorerapp.R
import com.example.bookexplorerapp.model.Book

class BookAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        val bookAuthor: TextView = itemView.findViewById(R.id.tvBookAuthor)
        val bookCover: ImageView = itemView.findViewById(R.id.ivBookCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bookTitle.text = book.title
        holder.bookAuthor.text = book.author ?: "Unknown Author"

        // Load book cover image
        val coverUrl = "https://covers.openlibrary.org/b/id/${book.coverId}-M.jpg"
        Glide.with(holder.itemView.context)
            .load(coverUrl)
            .placeholder(R.drawable.placeholder) // Add a placeholder image
            .into(holder.bookCover)

        // Handle item click and pass details to BookDetailsActivity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookDetailsActivity::class.java).apply {
                putExtra("bookTitle", book.title)
                putExtra("bookAuthor", book.author ?: "Unknown Author")
                putExtra("bookCoverUrl", coverUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = books.size
}
