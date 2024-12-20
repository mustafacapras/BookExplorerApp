package com.example.bookexplorerapp.model

data class BookDetailsResponse(
    val title: String?,
    val author: String?,
    val description: Any? // Handles both String and Object types
)
