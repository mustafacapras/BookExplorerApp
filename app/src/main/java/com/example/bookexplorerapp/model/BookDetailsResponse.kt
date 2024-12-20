package com.example.bookexplorerapp.model

data class BookDetailsResponse(
    val description: Description?
)

data class Description(
    val value: String // The actual description
)
