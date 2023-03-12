package com.geeks.ulul.ui.fragment.authAndReg.signUp

import com.geeks.ulul.core.base.BaseViewModel
import com.geeks.ulul.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    private val _signUpState = mutableUiStateFlow<Unit>()
    val signUpState = _signUpState.asStateFlow()

    fun signUp(
        name: String,
        username: String,
        email: String,
        password: String,
        password_again: String
    ) = authenticationRepository.signUp(
        name,
        username,
        email,
        password,
        password_again
    ).gatherRequest(_signUpState)
}