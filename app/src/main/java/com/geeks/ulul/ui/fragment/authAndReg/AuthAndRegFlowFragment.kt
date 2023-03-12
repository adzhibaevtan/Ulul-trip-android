package com.geeks.ulul.ui.fragment.authAndReg

import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import com.geeks.ulul.core.base.BaseFlowFragment
import com.geeks.ulul_trip_android.R

class AuthAndRegFlowFragment :
    BaseFlowFragment(R.layout.fragment_auth_and_reg_flow, R.id.nav_host_fragment_auth_and_reg){

    private lateinit var callback: OnBackPressedCallback
    override fun setupNavigation(navController: NavController) {
        super.setupNavigation(navController)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        callback.remove()
    }
}