package com.example.hilt_testing_experimentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hilt_testing_experimentation.data.YahooFinanceService
import com.example.hilt_testing_experimentation.data.model.MarketSummaryDto
import com.example.hilt_testing_experimentation.di.schedulers.Schedulers
import com.example.hilt_testing_experimentation.utils.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val yahooService: YahooFinanceService,
    private val schedulers: Schedulers
) : ViewModel() {

    private val _summary: MutableLiveData<Resource<MarketSummaryDto>> = MutableLiveData(Resource.loading())
    val summary: LiveData<Resource<MarketSummaryDto>>
        get() = _summary

    private var disposable = CompositeDisposable()

    init {
        getSummary()
    }

    private fun getSummary() {
        _summary.value = Resource.loading()
        yahooService.getSummary()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .subscribe(::setSummary, ::setError)
            .addTo(disposable)
    }

    private fun setSummary(summary: MarketSummaryDto) {
        _summary.value = Resource.success(summary)
    }

    private fun setError(throwable: Throwable) {
        _summary.value = Resource.error(throwable.message ?: "")
    }

    fun retry() {
        getSummary()
    }
}