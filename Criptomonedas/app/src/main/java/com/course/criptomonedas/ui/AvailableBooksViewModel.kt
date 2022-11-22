package com.course.criptomonedas.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.criptomonedas.data.db.dao.BooksDao
import com.course.criptomonedas.data.db.model.BooksEntity
import com.course.criptomonedas.data.models.ResponseBooks
import com.course.criptomonedas.domain.FilterCurrenciesUseCase
import com.course.criptomonedas.domain.GetAvailableBooksCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableBooksViewModel @Inject constructor(
    private val useCaseGetAvailableBooksCase: GetAvailableBooksCase,
    private val filterCurrenciesUseCase: FilterCurrenciesUseCase,
    private val dao: BooksDao
) : ViewModel() {

    private val _available_books = MutableLiveData<List<BooksEntity>>()
    val availableBooks = _available_books

    fun getAvailableBooks() = viewModelScope.launch(IO) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            useCaseGetAvailableBooksCase().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { onSucces: ResponseBooks?, onError: Throwable? ->
                    onSucces.let { lista ->
                        filterCurrenciesUseCase.invoke(
                            lista?.payload ?: emptyList()
                        ) { listFiltered ->
                            val entities = listFiltered.map { currency ->
                                BooksEntity(
                                    name = currency.book,
                                    id = currency.book
                                )
                            }

                            CoroutineScope(IO).launch {
                                dao.insertBook(books = entities)
                                val localData = dao.getBooks()
                                _available_books.postValue(localData)
                            }
                        }
                    }
                    onError.let { error ->
                        Log.d("TAG_BOOKS", "Error get availables book: $error")
                    }
                }
        )
    }
}
