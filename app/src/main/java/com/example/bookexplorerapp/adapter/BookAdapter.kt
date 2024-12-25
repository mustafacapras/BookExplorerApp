package com.example.bookexplorerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookexplorerapp.R
import com.example.bookexplorerapp.model.Book

class BookAdapter(
    private val books: List<Book>,
    private val onFavoriteClick: (Book) -> Unit,
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookTitle = itemView.findViewById<TextView>(R.id.tvBookTitle)
        private val bookAuthor = itemView.findViewById<TextView>(R.id.tvBookAuthor)
        private val bookImage = itemView.findViewById<ImageView>(R.id.ivBookCover)
        private val favoriteButton = itemView.findViewById<ImageView>(R.id.ivAddFavorite)

        fun bind(book: Book) {
            bookTitle.text = book.title
            bookAuthor.text = book.author
            Glide.with(itemView.context).load(book.imageUrl).into(bookImage)

            if (book.isFavorite) {
                favoriteButton.setImageResource(R.drawable.ic_favorite)
            } else {
                favoriteButton.setImageResource(R.drawable.ic_favorite_border)
            }

            // Handle favorite button click
            favoriteButton.setOnClickListener {
                book.isFavorite = !book.isFavorite
                notifyItemChanged(adapterPosition)
                onFavoriteClick(book)
            }

            // Handle book item click
            itemView.setOnClickListener {
                onBookClick(book)
            }
        }
    }
}
