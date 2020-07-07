package com.example.hilt_testing_experimentation.data

import com.example.hilt_testing_experimentation.data.model.MarketSummaryDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface YahooFinanceService {
    @GET("get-summary?region=US&lang=en")
    fun getSummary(): Single<MarketSummaryDto>
}