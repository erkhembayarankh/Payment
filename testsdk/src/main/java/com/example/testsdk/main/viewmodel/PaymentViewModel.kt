package com.example.testsdk.main.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdk.network.Network
import com.example.testsdk.network.Options.OptionsResponse
import com.example.testsdk.network.UIState
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    val optionsState: MutableState<UIState<OptionsResponse.Options?>?> = mutableStateOf(null)
    val loadingState = mutableStateOf(true)

    fun fetchOptions() = viewModelScope.launch {
        Network.fetchOptionsList(
            success = {
                loadingState.value = false
                optionsState.value = UIState.Success(it)
            },
            failure = {
                loadingState.value = false
                optionsState.value = UIState.Failure(it)
            }
        )
    }

}