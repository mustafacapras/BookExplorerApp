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
            id = key, // Kitap kimliği eşleştirildi
            title = title,
            author = author_name?.joinToString(", ") ?: "", // Eğer author_name null ise, boş bir string atanır
            imageUrl = "https://covers.openlibrary.org/b/id/$cover_i-L.jpg", // Görsel URL'si oluşturulur
            isFavorite = false // Favori durumu varsayılan olarak false
        )
    }
}
