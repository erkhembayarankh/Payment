package com.example.testsdk.network.Options

data class OptionsResponse (var data: List<PaymentOptions>){

    data class PaymentOptions(
        var name: String?,
        var imageUrl:String?,
        var type:String?,
        var slug:String?
    )
}