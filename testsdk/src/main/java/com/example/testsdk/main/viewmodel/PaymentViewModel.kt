package com.example.testsdk.main.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdk.network.Invoice.InvoiceResponse
import com.example.testsdk.network.Network
import com.example.testsdk.network.Options.OptionsResponse
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    val optionsState: MutableState<OptionsResponse.Options?> = mutableStateOf(null)
    val loading = mutableStateOf(true)

    fun fetchOptions() = viewModelScope.launch {
        Network.fetchOptionsList(success = {
            optionsState.value = it
            loading.value = false
        },
            failure = {
                loading.value = false
            }
        )
    }

}