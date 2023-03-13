package com.geeks.ulul.ui.fragment.main.profile

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        binding.btnFavourites.setOnClickListener {
            Toast.makeText(requireContext(), "Account successfully deleted", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigateSafely(R.id.action_profileFragment_to_favoriteToursFragment)
        }
        binding.btnLogOut.setOnClickListener {
            userPreferences.clearPreferences()
            activityNavController().navigateSafely(R.id.action_mainFlowFragment_to_authenticationFlowFragment)
        }
        binding.btnDeleteAccount.setOnClickListener {
            viewModel.deleteAccount()
        }
    }

    override fun initSubscribers() {
        viewModel.accountDeletionState.spectateUiState(success = {
//            userPreferences.clearPreferences()
            activityNavController().navigateSafely(R.id.action_mainFlowFragment_to_authenticationFlowFragment)
            Toast.makeText(requireContext(), "Account successfully deleted", Toast.LENGTH_SHORT)
                .show()
        })
    }
}