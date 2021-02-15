package com.example.filmsapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.filmsapplication.R
import com.example.filmsapplication.adapters.FilmAdapter
import com.example.filmsapplication.databinding.FragmentAllFilmsBinding
import com.example.filmsapplication.models.LoadingState
import com.example.filmsapplication.util.extansions.shareFilm
import com.example.filmsapplication.util.listener.FilmItemClickListener
import java.text.SimpleDateFormat
import java.util.*


class AllFilmsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var uiBinding: FragmentAllFilmsBinding
    private lateinit var filmAdapter: FilmAdapter
    private val viewModel: FilmsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        uiBinding = FragmentAllFilmsBinding.inflate(inflater, container, false)
        return uiBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    private fun initUI() {
        uiBinding.refreshLayout.setOnRefreshListener(this)
        filmAdapter = FilmAdapter(object : FilmItemClickListener {
            override fun like(position: Int) {
                val item = filmAdapter.getItemIfExist(position)?.apply {
                    mainListPosition = position
                    isFavorite = !isFavorite
                }
                item?.let { viewModel.updateFavoriteList(it) }
                filmAdapter.notifyItemChanged(position)
            }

            override fun share(position: Int) {
                val itemId = filmAdapter.getItemIfExist(position)?.id
                itemId?.let { shareFilm(it) }
            }
        })
        uiBinding.recyclerViewFilms.adapter = filmAdapter
    }

    private fun initObservers() {
        viewModel.mainFilmsList.observe(viewLifecycleOwner, { filmAdapter.submitList(it) })
        viewModel.loadingStateListener.observe(viewLifecycleOwner, { showLoadingState(it) })
        viewModel.mainListItemUpdater.observe(viewLifecycleOwner,
            { filmAdapter.notifyItemChanged(it) })
    }

    private fun showLoadingState(loadingState: LoadingState) {
        when (loadingState) {
            is LoadingState.InitialLoading -> showInitialLoading(true)
            is LoadingState.InitialLoadingFinis -> showInitialLoading(false)
            is LoadingState.Loading -> showLoading(true)
            is LoadingState.LoadingFinish -> showLoading(false)
            is LoadingState.Refresh -> showInitialLoading(false)
            is LoadingState.Error -> {
                showInitialLoading(false)
                showLoading(false)
                showMessage(getString(R.string.error_network_error))
            }
            is LoadingState.CacheData -> {
                showInitialLoading(false)
                showMessage(getString(R.string.error_network_cache_data))
            }
            is LoadingState.NoData -> {
                showInitialLoading(false)
                showLoadingDataError(true)
            }
        }
    }

    private fun showLoadingDataError(isShow: Boolean) {
        uiBinding.textError.isVisible = isShow
    }

    private fun showInitialLoading(isShow: Boolean) {
        uiBinding.refreshLayout.isRefreshing = isShow
    }

    private fun showLoading(isShow: Boolean) {
        uiBinding.loadingDataContainer.isVisible = isShow
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onRefresh() {
        viewModel.refreshFilms()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllFilmsFragment()
    }
}