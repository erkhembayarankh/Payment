package com.example.testsdk.network.Invoice

data class InvoiceResponse(var data: InvoiceData) {

    data class InvoiceData(
        var thirdpartyId: String?,
        var invoiceId: String?,
        var qr: QrData?,
        var redirectUrl:String?,
        var deeplinks: List<DeepLink>?
    )

    data class QrData(
        var text: String?,
        var base64: String?
    )

    data class DeepLink(
        var name: String?,
        var description: String?,
        var logo: String?,
        var link: String?,
    )

}