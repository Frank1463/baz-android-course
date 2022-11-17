package com.course.criptomonedas.core

import com.course.criptomonedas.Constants
import com.course.criptomonedas.data.network.AvailableBooksService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }

    private const val USER_AGENT = ""

    private val client =
        OkHttpClient.Builder().addInterceptor(interceptor).addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .header("User-Agent", USER_AGENT)
                    .build()
            )
        }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_CRIPTOS)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client.build())
        .build()
}

val service: AvailableBooksService =
    RetrofitClient.retrofit.create(AvailableBooksService::class.java)