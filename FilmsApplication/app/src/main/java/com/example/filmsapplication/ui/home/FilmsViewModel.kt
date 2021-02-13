package com.example.filmsapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.filmsapplication.BaseApplication
import com.example.filmsapplication.menagers.FilmsDataSource
import com.example.filmsapplication.menagers.FilmsDataSourceFactory
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.LoadingState
import com.example.filmsapplication.models.Resource
import com.example.filmsapplication.models.Response
import com.example.filmsapplication.repository.FilmRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FilmsViewModel : ViewModel() {

    @field:Inject
    lateinit var filmRepository: FilmRepository

    val filmsList: LiveData<PagedList<Film>>
    val loadingStateListener: MutableLiveData<LoadingState> = MutableLiveData()

    init {
        BaseApplication.appComponent.inject(this)
        val userDataSource =
            FilmsDataSourceFactory(filmRepository, viewModelScope, loadingStateListener)
        filmsList = LivePagedListBuilder(userDataSource, pagedListConfig()).build()
    }

    fun refreshFilms() {
       }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()


}