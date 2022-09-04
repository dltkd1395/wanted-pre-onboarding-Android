package com.qure.onboarding.ui.Top_News

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.qure.onboarding.*
import com.qure.onboarding.ui.Adapter.NewsAdapter
import com.qure.onboarding.AdatperListener.NewsAdapterListener
import com.qure.onboarding.Network.RetrofitObject
import com.qure.onboarding.viewModel.NewsViewModel
import com.qure.onboarding.model.News
import com.qure.onboarding.model.NewsBody
import com.qure.onboarding.databinding.FragmentNewsListBinding
import com.qure.onboarding.ui.NewsDetailFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsListFragment : Fragment(), NewsAdapterListener {

    private lateinit var binding : FragmentNewsListBinding
    private lateinit var topNewsAdapter : NewsAdapter
    private lateinit var viewModel : NewsViewModel


    private var datas = mutableListOf<News>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_news_list, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        initRecycle()

        return binding.root
    }

    private fun initRecycle() {

        RetrofitObject.getNewsService().getInfo().enqueue(object : Callback<NewsBody> {
            override fun onResponse(call: Call<NewsBody>, response: Response<NewsBody>) {
                datas = response.body()!!.articles.toMutableList()
                setAdapter(datas)

            }

            override fun onFailure(call: Call<NewsBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun setAdapter(datas : MutableList<News>){
        topNewsAdapter = NewsAdapter(requireContext(),this)
        topNewsAdapter.submitList(datas)
        binding.apply {
            topNewsRecyclerview.adapter = topNewsAdapter
            topNewsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onItemNews(item: News) {
        viewModel.sendNewsInfo(item)
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.main_frame, NewsDetailFragment()).addToBackStack(null)
            .commit()

    }

}