package com.example.bookexplorerapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookexplorerapp.model.Book
import com.example.bookexplorerapp.network.BookRepository
import com.example.bookexplorerapp.network.ApiService
import kotlinx.coroutines.launch
import android.util.Log

class MainPageViewModel(private val apiService: ApiService) : ViewModel() {

    private val repository = BookRepository(apiService)

    val bookList = MutableLiveData<List<Book>>()
    val errorMessage = MutableLiveData<String>()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchBooks(query)
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("MainPageViewModel", "Raw Response: $body")
                    val books = body?.docs?.map { it.toBook() } ?: emptyList()
                    bookList.postValue(books)
                    Log.d("MainPageViewModel", "Mapped Books: $books")
                } else {
                    errorMessage.postValue("Error: ${response.code()}")
                    Log.e("MainPageViewModel", "API Error: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Network Error: ${e.message}")
                Log.e("MainPageViewModel", "Exception: ${e.message}")
            }
        }
    }
}
