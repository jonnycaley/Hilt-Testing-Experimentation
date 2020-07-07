package com.example.hilt_testing_experimentation.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.hilt_testing_experimentation.data.YahooFinanceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun providesFinanceService(
        okHttpClient: OkHttpClient
    ): YahooFinanceService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(YahooFinanceService::class.java)
    }

    @Provides
    fun providesOkHttpInterceptor(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                // key for api
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                    builder.header("x-rapidapi-key", "c8e67c907dmsh416ede28f252ad7p171085jsn05b172a4fae6")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
            addInterceptor(
                // provides logs
                HttpLoggingInterceptor().apply {
                    level = (HttpLoggingInterceptor.Level.BODY)
                }
            )
            // provide chucker http output
            addInterceptor(ChuckerInterceptor(context))
        }
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
    }
}