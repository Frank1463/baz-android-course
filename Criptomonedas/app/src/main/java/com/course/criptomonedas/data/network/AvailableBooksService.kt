package com.course.criptomonedas.data.network

import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.models.OrderBook
import com.course.criptomonedas.data.models.ResponseBooks
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AvailableBooksService {

    @GET("available_books/")
    fun getBooks(): Single<ResponseBooks>

    @GET("ticker/")
    suspend fun getDetailBook(@Query("book") id: String): ModelDetails

    @GET("order_book/")
    suspend fun getBooksById(@Query("book") book: String): OrderBook
}
