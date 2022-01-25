package com.example.testsdk.network

import android.util.Log
import com.example.testsdk.network.Options.OptionsResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    const val BASE_URL = "https://payment.steppelink.mn/v1/sdk/"

    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }.build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiService: ApiInterface = retrofit.create(ApiInterface::class.java)

    var token = ""

    fun fetchOptionsList(success: (List<OptionsResponse.PaymentOptions>) -> Unit,failure: (String) -> Unit,){
        val call: Call<OptionsResponse> = apiService.fetchOptionsList()
        call.enqueue(object : Callback<OptionsResponse?> {
            override fun onResponse(
                call: Call<OptionsResponse?>?,
                response: Response<OptionsResponse?>,
            ) {
                Log.d("res",response.body().toString())
                val responseData = response.body()?.data ?: emptyList()
                success(responseData)
            }

            override fun onFailure(call: Call<OptionsResponse?>?, t: Throwable?) {
                // Log error here since request failed
            }
        })

    }
}