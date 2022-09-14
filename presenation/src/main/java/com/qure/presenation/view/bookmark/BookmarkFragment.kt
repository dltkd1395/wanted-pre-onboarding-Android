package com.qure.presenation.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.qure.domain.model.News
import com.qure.presenation.R
import com.qure.presenation.base.BaseFragment
import com.qure.presenation.databinding.FragmentBookmarkBinding
import com.qure.presenation.view.adapter.BookmarkAdapter
import com.qure.presenation.view.adapter.NewsPagingAdapter
import com.qure.presenation.view.topnews.TopNewsFragmentDirections
import com.qure.presenation.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {
    private val mainViewModel : MainViewModel by activityViewModels()
    private val bookmarkAdapter : BookmarkAdapter by lazy {
        BookmarkAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun initView() {

        mainViewModel.getBookmark()

        binding.bookmarkNewsRecyclerview.adapter = bookmarkAdapter
        collectFlow()
    }


    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.bookmarkNews.collect { newsList ->
                    bookmarkAdapter.submitList(newsList.toList())
                }
            }
        }
    }
    private fun doOnClick(item: News) {
        val action =
            BookmarkFragmentDirections.actionBookmarkFragmentToNewsDetailFragment(
                item
            )
        requireView().findNavController().navigate(action)
    }


}