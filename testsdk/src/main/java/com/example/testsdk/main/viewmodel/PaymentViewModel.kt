package com.example.testsdk.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdk.network.Network
import com.example.testsdk.network.Options.OptionsResponse
import com.example.testsdk.network.UIState
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    var totalAmount: Double = 0.0;
    var invoiceId = ""
    val optionsState = MutableLiveData<UIState<List<OptionsResponse.PaymentOptions>>>()

    fun fetchOptions() = viewModelScope.launch {
        Network.fetchOptionsList(success = {
            optionsState.value = UIState.Success(it)
        },
            failure = {

            }
        )
    }

}