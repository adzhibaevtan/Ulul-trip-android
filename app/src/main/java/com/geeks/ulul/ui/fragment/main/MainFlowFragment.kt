package com.geeks.ulul.ui.fragment.main

import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul_trip_android.R
import com.geeks.ulul.core.base.BaseFlowFragment
import com.geeks.ulul_trip_android.databinding.FragmentMainFlowBinding

class MainFlowFragment :
    BaseFlowFragment(R.layout.fragment_main_flow, R.id.nav_host_fragment_main) {

    private val binding by viewBinding(FragmentMainFlowBinding::bind)

    override fun setupNavigation(navController: NavController) {
        constructBottomNavigation(navController)
    }

    private fun constructBottomNavigation(navController: NavController) {
        setupWithNavController(binding.bottomNavigation, navController)
    }
}