package com.geeks.ulul.ui.fragment.main.profile

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.core.extention.activityNavController
import com.geeks.ulul.core.extention.navigateSafely
import com.geeks.ulul.data.local.pref.UserPreferences
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {
    override val binding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel by viewModels<ProfileViewModel>()

     @Inject
    lateinit var userPreferences: UserPreferences

    override fun initListeners() {
        binding.btnLogOut.setOnClickListener {
            userPreferences.accessToken = ""
            userPreferences.refreshToken = ""
            userPreferences.isAuthenticated = false
            activityNavController().navigateSafely(R.id.action_mainFlowFragment_to_authenticationFlowFragment)
        }
    }
}
