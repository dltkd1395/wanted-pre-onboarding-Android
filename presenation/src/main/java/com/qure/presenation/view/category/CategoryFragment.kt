package com.qure.presenation.view.category

import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.qure.domain.model.Category
import com.qure.presenation.R
import com.qure.presenation.base.BaseFragment
import com.qure.presenation.databinding.FragmentCategoryBinding
import com.qure.presenation.view.adapter.CategoryAdapter


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private val categoryAdapter : CategoryAdapter by lazy {
        CategoryAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun initView() {
        setAdapter()
    }

    private fun setAdapter(){
        categoryAdapter.submitList(categoryList)
        binding.apply {
            categoryRecyclerview.adapter = categoryAdapter
            categoryRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)

        }
    }


    private fun doOnClick(item: Category) {
        val action =
           CategoryFragmentDirections.actionCategoryFragmentToCategoryListFragment(
                item.category
            )
        requireView().findNavController().navigate(action)
    }

    companion object {
        private val categoryList = listOf<Category>(
            Category.Business,
            Category.Entertainment,
            Category.Genernal,
            Category.Health,
            Category.Science,
            Category.Sports,
            Category.Technology
        )
    }

}