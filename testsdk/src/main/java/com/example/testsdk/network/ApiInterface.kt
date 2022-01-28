package com.example.testsdk.network

import com.example.testsdk.network.Invoice.InvoiceResponse
import com.example.testsdk.network.Options.OptionsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("options")
    fun fetchOptionsList(): Call<OptionsResponse>

    @POST("invoices/{invoiceId}/{method}")
    fun fetchInvoices(
        @Path("invoiceId") invoiceId: String,
        @Path("method") method: String
    ): Call<InvoiceResponse>
}