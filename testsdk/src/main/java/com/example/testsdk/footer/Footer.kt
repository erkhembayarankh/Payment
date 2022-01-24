package com.example.testsdk
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Footer(modifier: Modifier, onClick: () -> Unit = {}) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Нийт".uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "80,000 ₮",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { onClick() }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        id = R.color.primaryButtonBG
                    )
                ), modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = "Төлбөр төлөх".uppercase(),
                    color = colorResource(id = R.color.primaryButtonFont)
                )
            }
        }

    }
}