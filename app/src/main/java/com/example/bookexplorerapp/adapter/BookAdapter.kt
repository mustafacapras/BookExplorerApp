package com.example.bookexplorerapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexplorerapp.BookDetailsActivity
import com.example.bookexplorerapp.R
import com.example.bookexplorerapp.model.Book

class BookAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvBookTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.title.text = book.title

        // Handle item click
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, BookDetailsActivity::class.java)
            intent.putExtra("BOOK_TITLE", book.title)
            intent.putExtra("BOOK_DESCRIPTION", "Description for ${book.title}") // Dummy description
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = books.size
}
