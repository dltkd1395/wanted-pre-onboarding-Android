package com.qure.onboarding.ui.Saved_News

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.qure.onboarding.ui.Adapter.NewsAdapter
import com.qure.onboarding.AdatperListener.NewsAdapterListener
import com.qure.onboarding.ui.Top_News.NewsDetailFragment
import com.qure.onboarding.viewModel.NewsViewModel
import com.qure.onboarding.R
import com.qure.onboarding.Room.SavedDatabase
import com.qure.onboarding.model.News
import com.qure.onboarding.databinding.FragmentSavedNewsListBinding


class SavedNewsListFragment : Fragment() , NewsAdapterListener {

    private lateinit var binding : FragmentSavedNewsListBinding
    private lateinit var db : SavedDatabase
    private lateinit var newsAdapter : NewsAdapter
    private lateinit var viewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_news_list, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        db = Room.databaseBuilder(
            requireContext(),
            SavedDatabase::class.java,
            "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val savedDao = db.savedDao()



        viewModel.news_list.value = savedDao.getAll().toMutableList()

        setAdapter()

        return binding.root
    }


    private fun setAdapter(){
        newsAdapter = NewsAdapter( requireContext(),this)
        viewModel.news_list.observe(viewLifecycleOwner, Observer {
            val saved_list = mutableListOf<News>()

            println(it)

            for(i in it.toMutableList()){
                var news = News(i.author,i.title,i.description,i.url,i.urlToImage,i.publishedAt,i.content)

                saved_list.add(news)
            }
            newsAdapter.submitList(saved_list)
        })


        binding.apply {
            savedNewsRecyclerview.adapter = newsAdapter
            savedNewsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onItemNews(item: News) {
        viewModel.sendNewsInfo(item)
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.main_frame, NewsDetailFragment()).addToBackStack(null)
            .commit()
    }
}