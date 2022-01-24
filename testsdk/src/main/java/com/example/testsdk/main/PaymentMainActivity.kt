package com.example.testsdk.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testsdk.finish.FinalActivity
import com.example.testsdk.Footer
import com.example.testsdk.R
import com.example.testsdk.paymentList.ListPaymentType


class PaymentMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val list = mutableListOf(
                "Худалдаа хөгжлийн банк",
                "Голомт банк",
                "Qpay",
                "Хаан банк",
                "Social Pay",
            )
            val painterList = mutableListOf(
                R.drawable.tdbm,
                R.drawable.golomt,
                R.drawable.qpay,
                R.drawable.khanbank,
                R.drawable.sp,
            )

            Scaffold(
                topBar = {
                    TopAppBar(backgroundColor = colorResource(id = R.color.primaryBG)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "",
                            Modifier.clickable { }
                        )
                    }
                },
                bottomBar = {
                    Footer(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(120.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                            ),
                        onClick = {
                            sendMessage()
                        }
                    )
                }
            ) {
                MainBody(banksList = list, painterList = painterList)
            }

        }
    }

    private fun sendMessage() {
        val message = "Hello"
        val intent = Intent(this, FinalActivity::class.java).apply {
            putExtra("hello", message)
        }
        startActivity(intent)
    }


    @Composable
    fun MainBody(banksList: List<String>, painterList: List<Int>) {
        val selectedIndex = remember {
            mutableStateOf(-1)
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.primaryBG)),
        ) {
            Text(
                text = "Төлбөрийн нөхцөл".uppercase(),
                Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = colorResource(id = R.color.primary)
            )
            ListPaymentType(
                paymentTypeList = banksList,
                painter = painterList,
                selected = selectedIndex.value,
            ) {
                selectedIndex.value = it
            }
        }
    }
}