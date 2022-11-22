package com.course.criptomonedas.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.repository.detailsbook.DetailBooksRepository
import com.course.criptomonedas.data.repository.orderbook.OrderBookRepository
import com.course.criptomonedas.domain.GetDetailBookCase
import com.course.criptomonedas.domain.GetOrderBookCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailBookViewModelTest() {

    @RelaxedMockK
    private lateinit var repository: DetailBooksRepository
    lateinit var getDetailBookCase: GetDetailBookCase

    @RelaxedMockK
    private lateinit var repositoryOrder: OrderBookRepository
    lateinit var getOrderBookCase: GetOrderBookCase

    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDetailBookCase = GetDetailBookCase(repository)
        getOrderBookCase = GetOrderBookCase(repositoryOrder)
    }

    @Test
    fun addProductTest() {
        val book = "btc_mxn"
        val getDetails = DetailBookViewModel(getDetailBookCase, getOrderBookCase)

        val observer = Observer<ModelDetails> {}

        try {
            getDetails.detailBooks.observeForever(observer)
            val result = getDetails.getAvailableBooks(book)

            assertThat(result,`is`(true))
        } finally {
            getDetails.detailBooks.removeObserver(observer)
        }

    }
}