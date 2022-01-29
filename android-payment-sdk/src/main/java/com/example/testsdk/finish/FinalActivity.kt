package com.example.testsdk.finish

import android.os.Bundle
import androidx.activity.viewModels
import com.example.testsdk.R
import com.example.testsdk.base.activity.BaseActivity
import com.example.testsdk.finish.viewmodel.FinalViewModel

class FinalActivity : BaseActivity() {
    private val viewModel: FinalViewModel by viewModels()

    companion object {
        const val PAYMENT_TYPE = "PAYMENT_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val slug = intent.getStringExtra(PAYMENT_TYPE).orEmpty()
        viewModel.paymentType = slug
        setContentView(R.layout.activity_final)
    }
}