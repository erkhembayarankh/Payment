package com.example.testsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class PaymentMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = mutableListOf(
                "Худалдаа хөгжлийн банк",
                "Голомт банк",
                "Qpay",
                "Хаан банк",
                "Social Pay"
            )
            val painterList = mutableListOf(
                R.drawable.tdbm,
                R.drawable.golomt,
                R.drawable.qpay,
                R.drawable.khanbank,
                R.drawable.sp
            )
            var selectedIndex = remember {
                mutableStateOf(0)
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE5E5E5)),
            ) {
                Text(
                    text = "Төлбөрийн нөхцөл",
                    Modifier
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF003399),
                )
                ListPaymentType(
                    paymentTypeList = list,
                    painter = painterList,
                    selected = selectedIndex.value,
                ) {
                    selectedIndex.value = it
                }

            }

        }
    }

    @Composable
    fun ListPaymentType(
        paymentTypeList: List<String>,
        painter: List<Int>,
        selected: Int,
        onClick: (Int) -> Unit,
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
        ) {
            items(
                paymentTypeList.size
            ) {
                val activeBorder = if (it == selected) Color(0xFF003399) else Color(0xFFDFDFDF)
                val borderWidth = if (it == selected) 2.dp else 1.dp
                BankCard(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .border(
                            border = BorderStroke(borderWidth, activeBorder),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(5.dp)
                        .clickable { onClick(it) },
                    painter = painterResource(id = painter[it]),
                    paymentTypeList[it],
                    type = "Картаар"
                )
            }
        }
    }

    @Composable
    fun BankCard(
        modifier: Modifier = Modifier, painter: Painter,
        description: String = "",
        type: String = ""
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
            )
            Column {
                Text(text = type, fontSize = 9.sp, color = Color.LightGray)
                Text(
                    text = description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }

        }
    }

}