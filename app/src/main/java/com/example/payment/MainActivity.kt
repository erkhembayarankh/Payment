package com.example.payment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.example.payment.databinding.ActivityMainBinding
import com.example.testsdk.main.PaymentMainActivity



class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn.setOnClickListener {
            sendMessage(it)
        }
    }

    private fun sendMessage(view: View) {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZXJjaGFudCI6eyJfaWQiOiI2MTkyMTBmZDBlYzcyMjRhN2FlYTJmM2YiLCJuYW1lIjoiRVNBTiJ9LCJ1c2VyIjp7InVzZXJuYW1lIjoiOTc2ODg4NTczMzdAbm9lbWFpbC5tbiIsIl9pZCI6IjYxYmJmOWQ4ODk0ZGQ0MzBkODM0NGExNCJ9LCJpYXQiOjE2NDM0Mjg4MjF9.idCkjbsHxsopIYBkL0Wi2627fbfnvBEj2N0RxQpOBag"
        val invoiceId = "61f4bbd51518ca4743c8e6b1"
        val intent = Intent(this, PaymentMainActivity::class.java)
        intent.putExtra(PaymentMainActivity.ACCESS_TOKEN, token)
        intent.putExtra(PaymentMainActivity.INVOICE_ID, invoiceId)
        startActivity(intent)
    }
}


