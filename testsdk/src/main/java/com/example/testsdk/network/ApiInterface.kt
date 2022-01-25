package com.example.testsdk.network

import com.example.testsdk.network.Options.OptionsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("options")
    fun fetchOptionsList(): Call<OptionsResponse>
}