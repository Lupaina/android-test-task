package com.example.filmsapplication.models

sealed class LoadingState {

    object InitialLoading : LoadingState()
    object InitialLoadingFinis : LoadingState()
    object Loading : LoadingState()
    object LoadingFinish : LoadingState()
    object Refresh : LoadingState()
    object Error : LoadingState()
    object CacheData : LoadingState()
    object NoData : LoadingState()
}