package com.example.testsdk.finish.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdk.network.Invoice.InvoiceResponse
import com.example.testsdk.network.Network
import com.example.testsdk.network.UIState
import kotlinx.coroutines.launch

class FinalViewModel : ViewModel() {

    val invoiceState: MutableState<UIState<InvoiceResponse.InvoiceData?>?> = mutableStateOf(null)
    var paymentType: String = ""
    val finalLoadingState = mutableStateOf(true)

    fun fetchPayment(invoiceId: String, method: String) = viewModelScope.launch {
        Network.fetchInvoices(
            invoiceId,
            method = method,
            success = {
                finalLoadingState.value = false
                invoiceState.value = UIState.Success(it)
            }, failure = {
                finalLoadingState.value = false
                UIState.Failure("FAILED !")
            })
    }
}
