package com.qure.onboarding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qure.onboarding.R
import com.qure.onboarding.ui.Categories.CategoriesFragment
import com.qure.onboarding.ui.Saved_News.SavedNewsListFragment
import com.qure.onboarding.ui.Top_News.NewsListFragment
import com.qure.onboarding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var newsListFragment: NewsListFragment? = null
    private var categoriesFragment: CategoriesFragment? = null
    private var savedNewsListFragment: SavedNewsListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationSelect()

    }


    private fun bottomNavigationSelect() {

        val fragmentTransaction = supportFragmentManager

        fragmentTransaction.beginTransaction().replace(R.id.main_frame, NewsListFragment()).commit()

        binding.bottomNavigation.run {
            setOnItemSelectedListener { item ->

                when (item.itemId) {

                    R.id.newslistFragment -> {

                        newsListFragment = NewsListFragment()
                        fragmentTransaction.beginTransaction()
                            .replace(R.id.main_frame, newsListFragment!!).commit()


                        return@setOnItemSelectedListener true
                    }
                    R.id.categoriesFragment -> {

                        categoriesFragment = CategoriesFragment()
                        fragmentTransaction.beginTransaction()
                            .replace(R.id.main_frame, categoriesFragment!!).commit()

                        return@setOnItemSelectedListener true
                    }
                    R.id.savedFragment -> {

                        savedNewsListFragment = SavedNewsListFragment()
                        fragmentTransaction.beginTransaction()
                            .add(R.id.main_frame, savedNewsListFragment!!).commit()
                        return@setOnItemSelectedListener true
                    }
                    else -> return@setOnItemSelectedListener true
                }
            }
            selectedItemId = R.id.newslistFragment
        }
    }
}