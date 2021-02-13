package com.example.filmsapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.filmsapplication.adapters.FilmAdapter
import com.example.filmsapplication.databinding.FragmentAllFilmsBinding
import com.example.filmsapplication.models.Resource


/**
 * A simple [Fragment] subclass.
 * Use the [AllFilmsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllFilmsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var uiBinding: FragmentAllFilmsBinding
    private val viewModel: FilmsViewModel by activityViewModels()
    private lateinit var filmAdapter: FilmAdapter

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
        filmAdapter = FilmAdapter()
        uiBinding.recyclerViewFilms.adapter = filmAdapter
    }

    private fun initObservers() {
        viewModel.filmsList.observe(viewLifecycleOwner, { resource ->
                filmAdapter.submitList(resource)
        })

        viewModel.loadingStateListener.observe(viewLifecycleOwner, { state ->
            Log.v("LOADING STATE", state.toString())
        })
    }

    private fun <T> showLoadingStare(result: Resource<T>) {
        when (result) {
            is Resource.Loading -> {
                showLoading(true)
                showLoadingError(false)
            }
            is Resource.Success -> {
                showLoading(false)
                showLoadingError(false)
            }
            is Resource.Error -> {
                showLoading(false)
                showLoadingError(true)
            }
        }
    }

    private fun showLoadingError(isShow: Boolean) {
        uiBinding.textError.isVisible = isShow
    }

    private fun showLoading(isShow: Boolean) {
        uiBinding.refreshLayout.isRefreshing = isShow
    }

    override fun onRefresh() {
        viewModel.refreshFilms()
    }

    companion object {
        private const val TAG = "AllFilmsFragment"

        @JvmStatic
        fun newInstance() = AllFilmsFragment()
    }
}