package com.example.testsdk.finish.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import coil.compose.rememberImagePainter
import com.example.testsdk.R
import com.example.testsdk.base.fragment.BaseFragment
import com.example.testsdk.finish.viewmodel.FinalViewModel
import com.example.testsdk.main.viewmodel.PaymentViewModel
import com.example.testsdk.network.Network
import java.util.*
import kotlin.concurrent.schedule


class FinalFragment : BaseFragment() {

    private val viewModel: FinalViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.fetchPayment(Network.invoiceId, method = viewModel.paymentType)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                val invoice = viewModel.invoiceState.value

                Scaffold(
                    topBar = {
                        TopAppBar(backgroundColor = colorResource(id = R.color.primaryBG)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "",
                                Modifier.clickable { getBaseActivity().onBackPressed() }
                            )
                        }
                    }
                ) {
//                    LazyGridFor(items = invoice?.deeplinks ?: emptyList(), rowSize = 3) {
//                        Image(
//                            painter = rememberImagePainter(it.logo.toString()),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(90.dp)
//                                .padding(10.dp)
//                                .shadow(8.dp)
//                                .clickable {
//                                    startActivity(
//                                        Intent(Intent.ACTION_VIEW).apply {
//                                            data = Uri.parse("https://www.google.com")
//                                        }
//                                    )
//                                }
//                        )
//                    }
                }
            }
        }
    }

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
}