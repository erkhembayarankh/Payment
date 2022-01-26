package com.example.testsdk.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.testsdk.R
import com.example.testsdk.network.Network


class PaymentMainActivity : AppCompatActivity() {
    companion object {
        const val INVOICE_ID = "INVOICEID"
        const val ACCESS_TOKEN = "TOKEN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val invoiceId = intent.getStringExtra(ACCESS_TOKEN).orEmpty()
        val token = intent.getStringExtra(ACCESS_TOKEN).orEmpty()
        Network.token = token
        Network.invoiceId = invoiceId
        Log.d("network token", Network.token)
        Log.d("invoice id", Network.invoiceId)
        setContentView(R.layout.activity_payment_main)
    }
}