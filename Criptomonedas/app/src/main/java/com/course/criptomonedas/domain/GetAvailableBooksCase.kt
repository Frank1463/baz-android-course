package com.course.criptomonedas.domain

import com.course.criptomonedas.data.models.ResponseBooks
import com.course.criptomonedas.data.repository.AvailableBooksRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAvailableBooksCase @Inject constructor(
    private val repository: AvailableBooksRepository
) {
    operator fun invoke(): Single<ResponseBooks> {
        return repository.getAvailableBooks()
    }
}
