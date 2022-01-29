package com.example.testsdk.finish.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.activityViewModels
import coil.compose.rememberImagePainter
import com.example.testsdk.R
import com.example.testsdk.base.fragment.BaseFragment
import com.example.testsdk.finish.viewmodel.FinalViewModel
import com.example.testsdk.network.Network
import com.example.testsdk.network.UIState
import com.example.testsdk.utils.LazyGridFor
import com.example.testsdk.utils.Loader
import java.util.*


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
                val isShowWebView = remember {
                    mutableStateOf(false)
                }

                val selectedBank = remember {
                    mutableStateOf("")
                }
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
                    Loader(viewModel.finalLoadingState.value)
                    when (invoice) {
                        is UIState.Success -> {
                            if (invoice.data?.deeplinks?.isEmpty() == false) {
                                LazyGridFor(
                                    items = invoice.data?.deeplinks ?: emptyList(),
                                    rowSize = 3,
                                ) {
                                    Image(
                                        painter = rememberImagePainter(it.logo.toString()),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(90.dp)
                                            .padding(10.dp)
                                            .shadow(8.dp)
                                            .clickable {
                                                selectedBank.value = it.link.toString()
                                                isShowWebView.value = true
                                            }
                                    )
                                }
                                WebPageScreen(
                                    urlToRender = selectedBank.value,
                                    isShow = isShowWebView.value
                                )
                            } else {
                                isShowWebView.value = true
                                WebPageScreen(
                                    urlToRender = invoice.data?.redirectUrl ?: "",
                                    isShow = isShowWebView.value
                                )
                            }
                        }
                        UIState.Loading -> {
                            Log.d("LOADIGN", "LOADING")
                        }
                        else -> {}
                    }

                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    fun WebPageScreen(urlToRender: String, isShow: Boolean) {
        if (isShow) {
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    settings.domStorageEnabled = true
                    settings.javaScriptEnabled = true
                    this.setOnKeyListener { v, keyCode, event ->
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == MotionEvent.ACTION_UP
                            && this.canGoBack()
                        ) {
                            this.goBack()
                            return@setOnKeyListener true
                        }
                        false
                    }
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            url: String?
                        ): Boolean {
                            val parsedUri = Uri.parse(url)
//                        if (parsedUri.scheme == "steppearena" && parsedUri.host == "tickets") {
//                            val intent = Intent()
//                            getBaseActivity().setResult(Activity.RESULT_OK, intent)
//                            getBaseActivity().finish()
//                        }
                            if (parsedUri.scheme == "https" || parsedUri.scheme == "http") {
                                return false
                            }
                            return try {
                                startActivity(
                                    Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(url.orEmpty())
                                    }
                                )
                                true
                            } catch (exception: Exception) {
                                val payBank: LinkedHashMap<String, String> = LinkedHashMap()
                                payBank["khanbank"] = "com.khanbank.retail"
                                payBank["statebank"] = "mn.statebank.mobilebank"
                                payBank["xacbank"] = "com.xacbank.mobile"
                                payBank["tdbbank"] = "mn.tdb.pay"
                                payBank["most"] = "mn.grapecity.mostmoney"
                                payBank["nibank"] = "mn.nibank.mobilebank"
                                payBank["ckbank"] = "mn.ckbank.smartbank2"
                                payBank["capitronbank"] = "mn.ecapitron"
                                payBank["bogdbank"] = "com.bogdbank.ebank.v2"
                                payBank["qpaywallet"] = "mn.qpay.wallet"
                                payBank["mn.moco.candy"] = "mn.mobicom.candy"
                                if (payBank.containsKey(parsedUri.scheme)) {
                                    startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(
                                                "https://play.google.com/store/apps/details?id=${
                                                    payBank.get(key = parsedUri.scheme)
                                                }"
                                            )
                                        )
                                    )
                                }
                                true
                            }
                        }
                    }
                    loadUrl(urlToRender)
                }
            }, update = {
                it.loadUrl(urlToRender)
            })
        }
    }
}


