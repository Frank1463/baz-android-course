package com.course.criptomonedas.domain

import com.course.criptomonedas.Constants
import com.course.criptomonedas.data.models.ModelBook
import javax.inject.Inject

class FilterCurrenciesUseCase @Inject constructor() {
    operator fun invoke(
        list: List<ModelBook>,
        filter: (List<ModelBook>) -> Unit
    ) {
        val filtered = list.filter {
            it.book.contains(Constants.TYPE_FILTER)
        }
        filter(filtered)
    }
}
