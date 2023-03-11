package com.geeks.ulul.ui.fragment.authAndReg.signUp

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.core.extention.navigateSafely
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {
    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModels<SignUpViewModel>()

    override fun initListeners() {
        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(
                binding.etName.text.toString(),
                binding.etUsername.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etConfirmPassword.text.toString()
            )
        }
        binding.tvSignIn.setOnClickListener {
            findNavController().navigateSafely(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    override fun initSubscribers() {
        viewModel.signUpState.spectateUiState(success = {
            Toast.makeText(requireContext(), "ok", Toast.LENGTH_SHORT).show()
        }, error = {
            Toast.makeText(requireContext(), "ne ok", Toast.LENGTH_SHORT).show()

        })
    }
}