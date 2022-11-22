package com.course.criptomonedas.data.repository

import com.course.criptomonedas.data.models.ResponseBooks
import io.reactivex.rxjava3.core.Single

interface AvailableBooksRepository {

    fun getAvailableBooks(): Single<ResponseBooks>
}
