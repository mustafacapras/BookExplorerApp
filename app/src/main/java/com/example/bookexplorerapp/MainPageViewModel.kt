package com.example.bookexplorerapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookexplorerapp.network.BookRepository
import com.example.bookexplorerapp.network.RetrofitClient
import kotlinx.coroutines.launch

class MainPageViewModel : ViewModel() {

    private val repository = BookRepository(RetrofitClient.getApiService())

    val bookList = MutableLiveData<List<String>>()
    val errorMessage = MutableLiveData<String>()

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val response = repository.getBooks()
                if (response.isSuccessful) {
                    // Map works to a list of book titles
                    bookList.postValue(response.body()?.works?.map { it.title } ?: emptyList())
                } else {
                    errorMessage.postValue("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Network error: ${e.message}")
            }
        }
    }
}
