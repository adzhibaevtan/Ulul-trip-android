package com.geeks.ulul.ui.fragment.authAndReg.signIn

import com.geeks.ulul.core.base.BaseViewModel
import com.geeks.ulul.data.remote.dto.SignInResultDto
import com.geeks.ulul.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    private val _signInState = mutableUiStateFlow<SignInResultDto>()
    val signInState = _signInState.asStateFlow()

    fun signIn(email: String, password: String) =
        authenticationRepository.signIn(email, password).gatherRequest(_signInState)
}