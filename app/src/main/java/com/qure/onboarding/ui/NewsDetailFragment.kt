package com.qure.onboarding.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.qure.onboarding.R
import com.qure.onboarding.Room.Saved
import com.qure.onboarding.Room.SavedDatabase
import com.qure.onboarding.viewModel.NewsViewModel
import com.qure.onboarding.model.News
import com.qure.onboarding.databinding.FragmentNewsDetailBinding


class NewsDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding
    private lateinit var db : SavedDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false)


        val viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            binding.apply {
                detail = News(news.author,news.title,news.description, news.url, news.urlToImage, news.publishedAt, news.content)

            }
        })

        db = Room.databaseBuilder(
            requireContext(),
            SavedDatabase::class.java, "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val savedDao = db.savedDao()



        binding.apply {

            viewModel.news.observe(viewLifecycleOwner, Observer{
                val check = savedDao.check(it.title!!,it.author!!)
                if(check>0){
                    newsDetailSaved.isActivated = true
                } else {
                    newsDetailSaved.isActivated = false
                }
            })

            toolbarNewsDetailBack.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().remove(this@NewsDetailFragment).commit()
            }

            newsDetailSaved.setOnClickListener{ star ->

                viewModel.news.observe(viewLifecycleOwner, Observer {
                    val count = savedDao.check(it.title!!,it.author!!)
                    val saved = Saved(
                        it.title!!,
                        it.author!!,
                        it.description!!,
                        it.url!!,
                        it.urlToImage!!,
                        it.publishedAt!!,
                        it.content!!
                    )
                    if(count<=0) {
                        savedDao.insert(saved)
                        viewModel.news_list.value = savedDao.getAll().toMutableList()
                        star.isActivated = !star.isActivated
                    } else {
                        savedDao.delete(saved)
                        viewModel.news_list.value = savedDao.getAll().toMutableList()
                        star.isActivated = !star.isActivated
                    }
                })
                println(savedDao.getAll())

            }
        }


        return binding.root


    }


}