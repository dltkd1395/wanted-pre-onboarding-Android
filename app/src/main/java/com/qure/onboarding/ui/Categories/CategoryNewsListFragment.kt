package com.qure.onboarding.ui.Categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.qure.onboarding.*
import com.qure.onboarding.ui.Adapter.NewsAdapter
import com.qure.onboarding.AdatperListener.NewsAdapterListener
import com.qure.onboarding.BindingAdatper.TextBindingAdapter
import com.qure.onboarding.Network.RetrofitObject
import com.qure.onboarding.viewModel.NewsViewModel
import com.qure.onboarding.model.Category
import com.qure.onboarding.model.News
import com.qure.onboarding.model.NewsBody
import com.qure.onboarding.databinding.FragmentCategoryNewsListBinding
import com.qure.onboarding.ui.Top_News.NewsDetailFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryNewsListFragment : Fragment(), NewsAdapterListener {

    private lateinit var binding: FragmentCategoryNewsListBinding
    private lateinit var catecotyNewsAdapter: NewsAdapter
    private lateinit var viewModel: NewsViewModel

    private var datas = mutableListOf<News>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_category_news_list,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        viewModel.item_category.observe(viewLifecycleOwner, Observer {
            initRecycle(it)
            TextBindingAdapter.CategoryText(binding.toolbarCategoryDetailTitle, it.name!!)
        })
        binding.apply {
            toolbarCategoryDetailBack.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .remove(this@CategoryNewsListFragment).commit()
            }
        }


        return binding.root
    }

    private fun initRecycle(category: Category) {

        RetrofitObject.getNewsService()
            .getCategory(AppContents.COUNTRY, category.name!!, AppContents.API_KEY)
            .enqueue(object : Callback<NewsBody> {
                override fun onResponse(call: Call<NewsBody>, response: Response<NewsBody>) {
                    datas = response.body()!!.articles.toMutableList()
                    println("data : " + datas)
                    setAdapter(datas)

                }

                override fun onFailure(call: Call<NewsBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })


    }

    private fun setAdapter(datas: MutableList<News>) {
        catecotyNewsAdapter = NewsAdapter(requireContext(), this)
        catecotyNewsAdapter.submitList(datas)
        binding.apply {
            cateoryListRecyclerview.adapter = catecotyNewsAdapter
            cateoryListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onItemNews(item: News) {
        viewModel.sendNewsInfo(item)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.main_frame, NewsDetailFragment()).addToBackStack(null)
            .commit()

    }

}