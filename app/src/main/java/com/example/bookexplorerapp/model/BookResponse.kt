package com.example.bookexplorerapp.model

data class BookResponse(
    val docs: List<BookDocument>
)

data class BookDocument(
    val title: String,
    val author_name: List<String>?, // List of author names
    val cover_i: Int? // Cover ID for thumbnails
) {
    fun toBook(): Book {
        return Book(
            title = title,
            author = author_name?.joinToString(", "), // Convert list to a single string
            coverId = cover_i
        )
    }
}
