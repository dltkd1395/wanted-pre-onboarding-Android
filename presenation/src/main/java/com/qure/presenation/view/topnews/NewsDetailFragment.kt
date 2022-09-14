package com.qure.presenation.view.topnews

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.qure.domain.usecase.CheckBoomarkUseCase
import com.qure.presenation.R
import com.qure.presenation.base.BaseFragment
import com.qure.presenation.databinding.FragmentNewsDetailBinding
import com.qure.presenation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(R.layout.fragment_news_detail) {

    val detailArgs by navArgs<NewsDetailFragmentArgs>()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initView() {


        binding.apply {
            detail = detailArgs.item

            this.toolbarNewsDetailBack.setOnClickListener { it.findNavController().popBackStack() }

            mainViewModel.apply {
                checkBookmark(detailArgs.item)
                check.observe(viewLifecycleOwner, Observer {
                    newsDetailSaved.isActivated = it
                })

            }


            this.newsDetailSaved.setOnClickListener { star ->
                savedNews(star)
            }
        }

    }


    private fun savedNews(star : View) {
        mainViewModel.apply {
            check.observe(viewLifecycleOwner, Observer {
                if (it) {
                    star.isActivated = !star.isActivated
                    deleteBookmark(detailArgs.item)

                } else {
                    star.isActivated = !star.isActivated
                    insertBookmark(detailArgs.item)
                }
            })


        }
    }
}