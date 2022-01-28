package com.example.testsdk.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.testsdk.R

@Composable
fun <T> LazyGridFor(
    items: List<T>,
    rowSize: Int = 1,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primaryBG))
    ) {
        items(items.chunked(rowSize)) { row ->
            Row(Modifier.fillParentMaxWidth()) {
                for ((index, item) in row.withIndex()) {
                    Box(
                        Modifier
                            .fillMaxWidth(1f / (rowSize - index))
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        itemContent(item)
                    }
                }
            }
        }
    }
}