package com.course.criptomonedas.data.datasource.availablebooks

import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.models.OrderBook
import com.course.criptomonedas.data.models.ResponseBooks
import com.course.criptomonedas.data.network.AvailableBooksService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: AvailableBooksService
) : RemoteDataSource {
    override fun getAvailableBooks(): Single<ResponseBooks> {
        return service.getBooks()
    }

    override suspend fun getDetailBooks(id: String): ModelDetails {
        return service.getDetailBook(id)
    }

    override suspend fun getOrderBook(book: String): OrderBook {
        return service.getBooksById(book)
    }
}