package com.geeks.ulul.ui.fragment.authAndReg.signIn

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.core.extention.activityNavController
import com.geeks.ulul.core.extention.navigateSafely
import com.geeks.ulul.data.local.pref.UserPreferences
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInViewModel>(R.layout.fragment_sign_in) {
    override val binding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel by viewModels<SignInViewModel>()

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun initListeners() {
        binding.btnSignIn.setOnClickListener {
            viewModel.signIn(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
            )
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigateSafely(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun initSubscribers() {
        viewModel.signInState.spectateUiState(success = {
            userPreferences.accessToken = getAuthenticationToken(it.tokens, true)
            userPreferences.refreshToken = getAuthenticationToken(it.tokens, false)
            userPreferences.isAuthenticated = true
            userPreferences.userID = it.id
            activityNavController().navigateSafely(R.id.action_authAndRegFlowFragment_to_mainFlowFragment)
        }, error = {
            Toast.makeText(requireContext(), "ne ok", Toast.LENGTH_SHORT).show()
        })
    }

    private fun getAuthenticationToken(
        tokenString: String,
        shouldGetAccessToken: Boolean
    ) = when (shouldGetAccessToken) {
        true -> {
            val tokenMap = tokenString.substring(1, tokenString.length - 1)
                .split(", ")
                .map { it.split(": ") }
                .associate { (k, v) -> k to v }
            tokenMap["'access'"]?.removeSurrounding("'")
        }
        false -> {
            val tokenMap = tokenString.substring(1, tokenString.length - 1)
                .split(", ")
                .map { it.split(": ") }
                .associate { (k, v) -> k to v }
            tokenMap["'refresh'"]?.removeSurrounding("'")
        }
    }

    private fun getUserId() {

    }
}