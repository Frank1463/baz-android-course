package com.course.criptomonedas.domain

import com.course.criptomonedas.data.models.ModelDetailBook
import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.repository.detailsbook.DetailBooksRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDetailBookCaseTest {

    @RelaxedMockK
    private lateinit var repository: DetailBooksRepository
    lateinit var getDetailBookCase: GetDetailBookCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDetailBookCase = GetDetailBookCase(repository)
    }

    @Test
    fun `when get info api empty`() = runBlocking {

        val book = "btc_mxn"
        //given
        coEvery { repository.getDetailBooks(book) } returns ModelDetails(
            ModelDetailBook(
                "", "", ""
            )
        )

        //when
        getDetailBookCase(book)

        //then
        coVerify(exactly = 1) { repository.getDetailBooks(book) }
    }

    @Test
    fun `when get info book`() = runBlocking {
        val book = "btc_mxn"
        val infoBook = ModelDetails(ModelDetailBook("10", "5", "1"))
        //given
        coEvery { repository.getDetailBooks(book) } returns infoBook

        //when
        val response = getDetailBookCase(book)

        //then
        coVerify(exactly = 1) { repository.getDetailBooks(book) }
        assert(infoBook == response)
    }
}