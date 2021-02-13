package com.example.filmsapplication.menagers

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.LoadingState
import com.example.filmsapplication.repository.FilmRepository
import kotlinx.coroutines.CoroutineScope

class FilmsDataSourceFactory(
    private val repository: FilmRepository,
    private val scope: CoroutineScope,
    private val loadingListener: MutableLiveData<LoadingState>
) : DataSource.Factory<Int, Film>() {

    private val source = MutableLiveData<FilmsDataSource>()

    override fun create(): DataSource<Int, Film> {
        val source = FilmsDataSource(repository, scope, loadingListener)
        this.source.postValue(source)
        return source
    }
}