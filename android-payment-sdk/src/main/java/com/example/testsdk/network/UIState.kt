package com.example.testsdk.network

sealed class UIState<out R> {
    object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class LoadMore<T>(val data: T) : UIState<T>()
    data class Failure(val errorMessage: String) : UIState<Nothing>()
}