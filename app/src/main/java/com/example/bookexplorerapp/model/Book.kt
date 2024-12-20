package com.example.bookexplorerapp.model

data class Book(
    val title: String,
    val author: String?,
    val coverId: Int?,
    val workId: String? // Add work ID for fetching details
)
