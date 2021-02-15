package com.example.filmsapplication.menagers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.LoadingState
import com.example.filmsapplication.models.Response
import com.example.filmsapplication.repository.FilmRepository
import kotlinx.coroutines.CoroutineScope
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
            val page = response?.page ?: 1
            val list = response?.results
            if (!list.isNullOrEmpty()) {
                callback.onResult(list, page, page + 1)
                loadingListener.postValue(LoadingState.InitialLoadingFinis)
                saveInitialPage(list)
            } else {
                getDataFromCache(callback)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Film>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Film>) {
        loadingListener.postValue(LoadingState.Loading)
        val page = params.key
        loadFilms(page) { response ->
            val list = response?.results
            if (!list.isNullOrEmpty()) {
                callback.onResult(list, page + 1)
                loadingListener.postValue(LoadingState.LoadingFinish)
            } else {
                loadingListener.postValue(LoadingState.LoadingFinish)
            }
        }
    }

    private fun loadFilms(page: Int, callback: (Response<Film>?) -> Unit) {
        scope.launch {
            try {
                val response = repository.getFilms(page)
                callback(response)
            } catch (e: Throwable) {
                Log.e(TAG, "films loading error", e)
                callback(null)
            }
        }
    }

    private fun saveInitialPage(list: List<Film>) {
        scope.launch {
            repository.clearDBData()
            repository.savePage(list)
        }
    }

    private fun getDataFromCache(callback: LoadInitialCallback<Int, Film>) {
        scope.launch {
            val cacheList = repository.getFirstPage()
            if (cacheList.isNotEmpty()) {
                callback.onResult(cacheList, null, null)
                loadingListener.postValue(LoadingState.CacheData)
            } else {
                loadingListener.postValue(LoadingState.NoData)
            }
        }
    }

    companion object {
        private const val TAG = "FilmsDataSource"
    }

}