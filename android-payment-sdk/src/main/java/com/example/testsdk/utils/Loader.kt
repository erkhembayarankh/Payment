package com.example.testsdk.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.testsdk.R

@Composable
fun Loader(state: Boolean) {
    if (state) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            restartOnPlay = true,
            speed = 1.5F
        )
        val size = 120.dp
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.primaryBG)),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LottieAnimation(
                    composition,
                    progress,
                    modifier = Modifier
                        .width(size)
                        .height(size),
                )
//                Text(text = "Түр хүлээнэ үү")
            }
        }
    }
}
