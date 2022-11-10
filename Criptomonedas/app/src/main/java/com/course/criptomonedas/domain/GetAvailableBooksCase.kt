package com.course.criptomonedas.domain

import com.course.criptomonedas.data.models.ModelBook
import com.course.criptomonedas.data.repository.AvailableBooksRepository

class GetAvailableBooksCase(private val repository: AvailableBooksRepository) {

    suspend operator fun invoke(): List<ModelBook> {
        return repository.getAvailableBooks().payload.filter {
            it.book.contains("mxn")
        }
    }
}