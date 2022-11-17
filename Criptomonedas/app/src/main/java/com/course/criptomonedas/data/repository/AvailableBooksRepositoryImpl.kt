package com.course.criptomonedas.data.repository

import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSource
import com.course.criptomonedas.data.models.ResponseBooks
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AvailableBooksRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : AvailableBooksRepository {

    override fun getAvailableBooks(): Single<ResponseBooks> = dataSource.getAvailableBooks()
}