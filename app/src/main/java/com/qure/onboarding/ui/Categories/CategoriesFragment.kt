package com.qure.onboarding.ui.Categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.qure.onboarding.ui.Adapter.CategoryAdatper
import com.qure.onboarding.AdatperListener.CategoryAdapterLiistener
import com.qure.onboarding.viewModel.NewsViewModel
import com.qure.onboarding.R
import com.qure.onboarding.model.Category
import com.qure.onboarding.model.CategoryList
import com.qure.onboarding.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment(), CategoryAdapterLiistener {

    private lateinit var binding : FragmentCategoriesBinding
    private lateinit var categoryList: CategoryList
    private lateinit var categoryAdapter: CategoryAdatper
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        categoryList= CategoryList()
        viewModel.getCategory(categoryList.categoryList)

        setAdapter()

        return binding.root
    }




    private fun setAdapter(){
        categoryAdapter = CategoryAdatper(this)
        viewModel.category.observe(viewLifecycleOwner, Observer {
            categoryAdapter.submitList(it)
        })

        binding.apply {
            categoryRecyclerview.adapter = categoryAdapter
            categoryRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    override fun onItemCategory(item: Category) {
        viewModel.getCategoryName(item)

        requireActivity().supportFragmentManager.beginTransaction().add(R.id.main_frame, CategoryNewsListFragment()).addToBackStack(null)
            .commit()
    }


}