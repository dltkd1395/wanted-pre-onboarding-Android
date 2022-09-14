package com.qure.presenation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.qure.domain.model.News
import com.qure.domain.usecase.*
import com.qure.presenation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsRepoUseCase : GetNewsRepoUseCase,
    private val getBookmarkUseCase: GetBookmarkUseCase,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    private val checkBoomarkUseCase: CheckBoomarkUseCase

) : BaseViewModel() {


    private val _eventNewsRepo = MutableStateFlow<PagingData<News>>(PagingData.empty())
    val eventNewsRepo = _eventNewsRepo.asStateFlow()

    private val _bookmarkNews = MutableStateFlow<List<News>>(emptyList())
    val bookmarkNews = _bookmarkNews.asStateFlow()

    val check = MutableLiveData<Boolean>()

    fun getNewsRepo(category: String?) {
        viewModelScope.launch {

            getNewsRepoUseCase.getNews(this@MainViewModel, category)
                ?.cachedIn(viewModelScope)?.collectLatest { newsList ->

                    _eventNewsRepo.emit(newsList)
                }
        }
    }


    fun getBookmark() {
        CoroutineScope(Dispatchers.IO).launch {
            _bookmarkNews.update {
                getBookmarkUseCase()
            }
        }

    }

    fun checkBookmark(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            if (checkBoomarkUseCase.getCountBookmark(news)>0) {
                check.postValue(true)
            } else {
                check.postValue(false)
            }
        }
    }

    fun deleteBookmark(news : News) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteBookmarkUseCase(news)
        }
    }

    fun insertBookmark(news : News) {
        CoroutineScope(Dispatchers.IO).launch {
            insertBookmarkUseCase(news)
        }
    }


}