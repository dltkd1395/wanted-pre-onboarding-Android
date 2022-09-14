package com.qure.presenation.view

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.qure.presenation.R
import com.qure.presenation.base.BaseActivity

import com.qure.presenation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun init() {
        println("main activity init")
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        with(binding.bottomNavigation) {
            setupWithNavController(navController)
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.topNewsFragment -> {
                        println("topNewsFragment")
                        navController.navigate(R.id.topNewsFragment)
                    }
                    R.id.categoryFragment -> {
                        println("categoryFragment")
                        navController.navigate(R.id.categoryFragment)
                    }
                    R.id.bookmarkFragment -> {
                        println("bookmarkFragment")
                        navController.navigate(R.id.bookmarkFragment)
                    }
                }
                true
            }
        }
    }


}