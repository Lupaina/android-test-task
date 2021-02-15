package com.example.filmsapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.filmsapplication.BaseApplication
import com.example.filmsapplication.menagers.FilmsDataSourceFactory
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.LoadingState
import com.example.filmsapplication.repository.FilmRepository
import javax.inject.Inject

class FilmsViewModel : ViewModel() {

    @field:Inject
    lateinit var filmRepository: FilmRepository

    var mainFilmsList: LiveData<PagedList<Film>>
    val loadingStateListener: MutableLiveData<LoadingState> = MutableLiveData()
    val favouriteFilmsList: MutableLiveData<MutableList<Film>> = MutableLiveData(mutableListOf())
    val mainListItemUpdater = MutableLiveData<Int>()

    init {
        BaseApplication.appComponent.inject(this)
        mainFilmsList = initPagedListBuilder()
    }

    fun refreshFilms() {
        mainFilmsList.value?.dataSource?.invalidate()
        favouriteFilmsList.value = mutableListOf()
    }

    private fun initPagedListBuilder(): LiveData<PagedList<Film>> {
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        val userDataSource =
            FilmsDataSourceFactory(filmRepository, viewModelScope, loadingStateListener)
        return LivePagedListBuilder(userDataSource, pagedListConfig).build()
    }

    fun updateFavoriteList(item: Film) {
        favouriteFilmsList.addToListIfFavourite(item)
    }

    fun updateItemInMainList(item: Film) {
        mainListItemUpdater.value = item.mainListPosition
    }

    private fun MutableLiveData<MutableList<Film>>.addToListIfFavourite(item: Film) {
        val list = value
        list?.let {
            if (item.isFavorite) {
                it.add(item)
            } else {
                it.remove(item)
            }
            value = it
        }

    }
}