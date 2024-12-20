package com.example.bookexplorerapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookexplorerapp.model.Book
import com.example.bookexplorerapp.network.BookRepository
import com.example.bookexplorerapp.network.RetrofitClient
import kotlinx.coroutines.launch

class MainPageViewModel : ViewModel() {

    private val repository = BookRepository(RetrofitClient.getApiService())

    val bookList = MutableLiveData<List<Book>>()
    val errorMessage = MutableLiveData<String>()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchBooks(query)
                if (response.isSuccessful) {
                    bookList.postValue(response.body()?.docs?.map { it.toBook() } ?: emptyList())
                } else {
                    errorMessage.postValue("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Network error: ${e.message}")
            }
        }
    }
}
