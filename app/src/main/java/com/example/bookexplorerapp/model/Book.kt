package com.example.bookexplorerapp.model

data class Book(
    val id: String, // Kitap kimliği
    val title: String,
    val author: String,
    val imageUrl: String,
    var isFavorite: Boolean = false // Favori durumu
)
