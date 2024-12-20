package com.example.bookexplorerapp.model

data class BookResponse(
    val docs: List<BookDocument>
)

data class BookDocument(
    val title: String,
    val author_name: List<String>?,
    val cover_i: Int?,
    val key: String // Work ID
) {
    fun toBook(): Book {
        return Book(
            title = title,
            author = author_name?.joinToString(", "),
            coverId = cover_i,
            workId = key // Extract work ID
        )
    }
}
