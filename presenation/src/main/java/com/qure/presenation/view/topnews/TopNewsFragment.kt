package com.qure.presenation.view.topnews


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.qure.domain.model.News
import com.qure.presenation.R
import com.qure.presenation.base.BaseFragment
import com.qure.presenation.databinding.FragmentTopNewsBinding
import com.qure.presenation.view.adapter.NewsPagingAdapter
import com.qure.presenation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopNewsFragment : BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news) {

    private val mainViewModel : MainViewModel by activityViewModels()
    private val newsAdapter : NewsPagingAdapter by lazy {
        NewsPagingAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun initView() {

        mainViewModel.getNewsRepo(null)

        binding.topNewsRecyclerview.adapter = newsAdapter
        collectFlow()
    }


    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.eventNewsRepo.collectLatest { newsList ->

                    newsAdapter.submitData(newsList)
                }
            }
        }
    }



    private fun doOnClick(item: News) {
        val action =
            TopNewsFragmentDirections.actionTopNewsFragmentToNewsDetailFragment(
                item
            )
        requireView().findNavController().navigate(action)
    }




}