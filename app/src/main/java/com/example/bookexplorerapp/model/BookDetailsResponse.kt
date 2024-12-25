package com.example.bookexplorerapp.model

data class BookDetailsResponse(
    val title: String?,
    val authors: List<Map<String, String>>?, // Yazar bilgileri bir liste içinde olabilir
    val description: Any?, // Description bazen nesne, bazen string olabilir
    val covers: List<Int>? // Kapak ID'lerini tutan liste
) {
    val author: String?
        get() = authors?.joinToString(", ") { it["name"].orEmpty() }
    val coverUrl: String?
        get() = covers?.firstOrNull()?.let { "https://covers.openlibrary.org/b/id/$it-L.jpg" }
}
