package com.example.core.di.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {
    @Provides
    @IntoSet
    fun provideYahooInterceptor(): Interceptor =
        Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
            builder.header("x-rapidapi-key", "c8e67c907dmsh416ede28f252ad7p171085jsn05b172a4fae6")
            return@Interceptor chain.proceed(builder.build())
        }

    @Provides
    @IntoSet
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = (HttpLoggingInterceptor.Level.BODY)
        }

    @Provides
    @IntoSet
    fun provideChuckerInterceptor(@ApplicationContext context: Context): Interceptor  =
        ChuckerInterceptor(context)

    @Provides
    @IntoSet
    fun provideStethoInterceptor(): Interceptor = StethoInterceptor()
}