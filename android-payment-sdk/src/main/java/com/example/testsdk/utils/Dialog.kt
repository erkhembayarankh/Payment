package com.example.testsdk.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.testsdk.R

@Composable
fun showAlert(
    value: Boolean,
    onClick: () -> Unit
) {
    if (value) {
        AlertDialog(
            onDismissRequest = {
                onClick()
            },
            title = {
                Text(text = "Төлбөрийн хэлбэрээ сонгоно уу")
            },
            confirmButton = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 10.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primaryButtonBG)),
                        onClick = {
                            onClick()
                        }) {
                        Text(
                            "OK",
                            color = colorResource(id = R.color.primaryButtonFont),
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                }

            },
        )
    }
}
