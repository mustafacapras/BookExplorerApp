package com.example.bookexplorerapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexplorerapp.BookDetailsActivity
import com.example.bookexplorerapp.R

class BookAdapter(private val bookTitles: List<String>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val title = bookTitles[position]
        holder.bookTitle.text = title

        // Handle click
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookDetailsActivity::class.java).apply {
                putExtra("bookTitle", title) // Pass book title
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = bookTitles.size
}
