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
        val message = "Hello"
        val intent = Intent(this, PaymentMainActivity::class.java).apply {
            putExtra("hello", message)
        }
        startActivity(intent)
    }
}


