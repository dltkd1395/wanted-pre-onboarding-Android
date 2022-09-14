package com.qure.presenation.view.category

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.qure.domain.model.News
import com.qure.presenation.R
import com.qure.presenation.base.BaseFragment
import com.qure.presenation.databinding.FragmentCategoryListBinding
import com.qure.presenation.view.adapter.NewsPagingAdapter
import com.qure.presenation.view.adapter.bindingadapter.TextBindingAdapter
import com.qure.presenation.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoryListFragment : BaseFragment<FragmentCategoryListBinding>(R.layout.fragment_category_list) {

    val categoryArgs by navArgs<CategoryListFragmentArgs>()
    private val mainViewModel : MainViewModel by activityViewModels()
    private val newsAdapter : NewsPagingAdapter by lazy {
        NewsPagingAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun initView() {

        mainViewModel.getNewsRepo(categoryArgs.category)
        binding.apply {
            cateoryListRecyclerview.adapter = newsAdapter
            TextBindingAdapter.CategoryText(
                toolbarCategoryDetailTitle,
                categoryArgs.category
            )
            toolbarCategoryDetailBack.setOnClickListener { it.findNavController().popBackStack() }
        }
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
            CategoryListFragmentDirections.actionCategoryListFragmentToNewsDetailFragment(
                item
            )
        requireView().findNavController().navigate(action)
    }



}