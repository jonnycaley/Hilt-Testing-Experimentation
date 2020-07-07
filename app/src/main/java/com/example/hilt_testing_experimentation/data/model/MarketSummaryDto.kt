package com.example.hilt_testing_experimentation.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MarketSummaryDto(
    @SerializedName("marketSummaryResponse")
    @Expose
    val response: MarketSummaryResponse
) {
    class MarketSummaryResponse(
        @SerializedName("result")
        @Expose
        val stocks: List<Stock>?  = null
    ) {
        class Stock(
            @SerializedName("fullExchangeName")
            @Expose
            var fullExchangeName: String? = null,

            @SerializedName("exchangeTimezoneName")
            @Expose
            var exchangeTimezoneName: String? = null,

            @SerializedName("symbol")
            @Expose
            var symbol: String? = null,

            @SerializedName("gmtOffSetMilliseconds")
            @Expose
            var gmtOffSetMilliseconds: Long? = null,

            @SerializedName("exchangeDataDelayedBy")
            @Expose
            var exchangeDataDelayedBy: Long? = null,

            @SerializedName("firstTradeDateMilliseconds")
            @Expose
            var firstTradeDateMilliseconds: Long? = null,

            @SerializedName("language")
            @Expose
            var language: String? = null,

            @SerializedName("exchangeTimezoneShortName")
            @Expose
            var exchangeTimezoneShortName: String? = null,

            @SerializedName("quoteType")
            @Expose
            var quoteType: String? = null,

            @SerializedName("marketState")
            @Expose
            var marketState: String? = null,

            @SerializedName("market")
            @Expose
            var market: String? = null,

            @SerializedName("quoteSourceName")
            @Expose
            var quoteSourceName: String? = null,

            @SerializedName("priceHint")
            @Expose
            var priceHint: Long? = null,

            @SerializedName("tradeable")
            @Expose
            var tradeable: Boolean? = null,

            @SerializedName("sourceInterval")
            @Expose
            var sourceInterval: Long? = null,

            @SerializedName("exchange")
            @Expose
            var exchange: String? = null,

            @SerializedName("shortName")
            @Expose
            var shortName: String? = null,

            @SerializedName("region")
            @Expose
            var region: String? = null,

            @SerializedName("triggerable")
            @Expose
            var triggerable: Boolean? = null,

            @SerializedName("headSymbolAsString")
            @Expose
            var headSymbolAsString: String? = null,

            @SerializedName("headSymbol")
            @Expose
            var headSymbol: Boolean? = null,

            @SerializedName("contractSymbol")
            @Expose
            var contractSymbol: Boolean? = null,

            @SerializedName("currency")
            @Expose
            var currency: String? = null,

            @SerializedName("longName")
            @Expose
            var longName: String? = null
        )
    }
}