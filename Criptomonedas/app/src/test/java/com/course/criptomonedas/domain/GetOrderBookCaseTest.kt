package com.course.criptomonedas.domain

import com.course.criptomonedas.data.models.Ask
import com.course.criptomonedas.data.models.Bid
import com.course.criptomonedas.data.models.ModelAsksBids
import com.course.criptomonedas.data.models.OrderBook
import com.course.criptomonedas.data.repository.orderbook.OrderBookRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOrderBookCaseTest {
    @RelaxedMockK
    private lateinit var repository: OrderBookRepository
    lateinit var getOrderBookCase: GetOrderBookCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getOrderBookCase = GetOrderBookCase(repository)
    }

    @Test
    fun `when get info api empty`() = runBlocking {

        val book = "btc_mxn"
        //given
        coEvery { repository.getOrderBooks(book) } returns OrderBook(
            ModelAsksBids(
                emptyList(),
                emptyList(),
                ""
            )
        )

        //when
        getOrderBookCase(book)

        //then
        coVerify(exactly = 1) { repository.getOrderBooks(book) }
    }

    @Test
    fun `when get info ask and bids`() = runBlocking {
        val book = "btc_mxn"
        val asks = listOf(
            Ask("10", "15"),
            Ask("15", "25")
        )
        val bids = listOf(
            Bid("20", "35"),
            Bid("20", "35")
        )

        val infoBook = OrderBook(
            ModelAsksBids(
                asks,
                bids,
                "Sab 19 de Noviembre"
            )
        )
        //given
        coEvery { repository.getOrderBooks(book) } returns infoBook

        //when
        val response = getOrderBookCase(book)

        //then
        coVerify(exactly = 1) { repository.getOrderBooks(book) }
        assert(infoBook == response)
    }
}

