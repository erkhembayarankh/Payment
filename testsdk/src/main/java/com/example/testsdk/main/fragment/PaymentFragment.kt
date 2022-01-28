package com.example.testsdk.main.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import com.example.testsdk.Footer
import com.example.testsdk.R
import com.example.testsdk.base.fragment.BaseFragment
import com.example.testsdk.finish.FinalActivity
import com.example.testsdk.finish.fragment.FinalFragment
import com.example.testsdk.main.viewmodel.PaymentViewModel
import com.example.testsdk.network.Network
import com.example.testsdk.paymentList.ListPaymentType

class PaymentFragment : BaseFragment() {

    private val viewModel: PaymentViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.fetchOptions()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                val selectedIndex = remember {
                    mutableStateOf(-1)
                }
                Scaffold(
                    topBar = {
                        TopAppBar(backgroundColor = colorResource(id = R.color.primaryBG)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "",
                                Modifier.clickable {
                                    getBaseActivity().onBackPressed()
                                }
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
                                if (selectedIndex.value != -1) {
                                    val intent = Intent(activity, FinalActivity::class.java)
                                    val slug =
                                        viewModel.optionsState.value?.options?.get(selectedIndex.value)?.slug
                                    intent.putExtra(FinalActivity.PAYMENT_TYPE, slug)
                                    startActivity(intent)
                                }
                            }
                        )
                    }
                ) {
                    MainBody(viewModel = viewModel, selectedIndex = selectedIndex)
                }

            }

        }
    }


    @Composable
    fun MainBody(
        viewModel: PaymentViewModel,
        selectedIndex: MutableState<Int>
    ) {

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
                viewModel = viewModel,
                selected = selectedIndex.value,
            ) {
                selectedIndex.value = it
            }
        }
    }
}

