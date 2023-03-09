package com.geeks.ulul.ui.fragment.splash

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.core.extention.navigateSafely
import com.geeks.ulul.data.local.pref.UserPreferences
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>(R.layout.fragment_splash_screen) {
    override val binding: FragmentSplashScreenBinding by viewBinding(FragmentSplashScreenBinding::bind)
    override val viewModel by viewModels<SplashScreenViewModel>()

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun constructListeners() {
        val avd = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.ic_snail_anim)

        binding.logoAnim.setImageDrawable(avd)
        binding.logoAnim.animate()
            .alpha(1f)
            .setDuration(3000L)
            .withEndAction {
                when {
                    userPreferences.isAuthenticated -> {
                    findNavController().navigateSafely(R.id.action_splashScreenFragment_to_mainFlowFragment)
                }
                    !userPreferences.isAuthenticated -> {
                    findNavController().navigateSafely(R.id.action_splashScreenFragment_to_authAndRegFlowFragment)
                    }
                }
            }
            .start()

        avd?.start()

    }
}