package com.example.filmsapplication.menagers

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.LoadingState
import com.example.filmsapplication.models.Response
import com.example.filmsapplication.repository.FilmRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FilmsDataSource(
    private val repository: FilmRepository,
    private val scope: CoroutineScope,
    private val loadingListener: MutableLiveData<LoadingState>
) : PageKeyedDataSource<Int, Film>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Film>
    ) {
        loadingListener.postValue(LoadingState.InitialLoading)
        loadFilms(1) { response ->
            val list = response?.results
            if (!list.isNullOrEmpty()) {
                callback.onResult(list, 1, 2)
                loadingListener.postValue(LoadingState.InitialLoadingFinis)
            } else {
                loadingListener.postValue(LoadingState.Error)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Film>) {
        loadingListener.postValue(LoadingState.Loading)
        val page = params.key

        loadFilms(page) { response ->
            val list = response?.results
            if (!list.isNullOrEmpty()) {
                callback.onResult(list, page + 1)
                loadingListener.postValue(LoadingState.InitialLoadingFinis)
            }else{
                loadingListener.postValue(LoadingState.Error)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Film>) {
        loadingListener.postValue(LoadingState.Loading)
        val page = params.key
        loadFilms(page) { response ->
            val list = response?.results
            if (!list.isNullOrEmpty()) {
                callback.onResult(list, page + 1)
                loadingListener.postValue(LoadingState.LoadingFinish)
            } else {
                loadingListener.postValue(LoadingState.Error)
            }
        }
    }

    private fun loadFilms(page: Int, callback: (Response<Film>?) -> Unit) {
        scope.launch {
            try {
                val response = repository.getFilms(page)
                callback(response)
            } catch (e: Throwable) {
                callback(null)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}