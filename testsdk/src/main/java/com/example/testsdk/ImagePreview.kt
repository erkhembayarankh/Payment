package com.example.testsdk

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable

fun ListPaymentType(paymentTypeList: List<String>,painter: Painter, onClick:()->Unit = {}){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            paymentTypeList.size
        ) {
            BankCard(
                Modifier
                    .padding(vertical = 8.dp, horizontal = 10.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onClick() },
                painter = painter,
                paymentTypeList[it]
            )
        }
    }
}

@Composable
fun BankCard(
    modifier: Modifier = Modifier, painter: Painter,
    description: String = "",
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
        Text(
            text = description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}
