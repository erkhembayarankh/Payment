package com.example.testsdk.paymentList

import BankCard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testsdk.network.Options.OptionsResponse
import com.example.testsdk.network.UIState


@Composable
fun ListPaymentType(
    selected: Int,
    options:  OptionsResponse.Options,
    onClick: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
        modifier = Modifier.padding(bottom = 120.dp)
    ) {
        items(
            options.options.size
        ) {
            val bankOptions = options.options
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
                    imageUrl = bankOptions[it].imageUrl.orEmpty(),
                    bankOptions[it].name.orEmpty(),
                    type = "Картаар"
                )
        }
    }
}