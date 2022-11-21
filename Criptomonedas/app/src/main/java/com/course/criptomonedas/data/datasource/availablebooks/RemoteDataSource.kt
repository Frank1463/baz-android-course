package com.course.criptomonedas.data.datasource.availablebooks

import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.models.OrderBook
import com.course.criptomonedas.data.models.ResponseBooks
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun getAvailableBooks(): Single<ResponseBooks>
    suspend fun getDetailBooks(id: String): ModelDetails
    suspend fun getOrderBook(book: String): OrderBook
}
