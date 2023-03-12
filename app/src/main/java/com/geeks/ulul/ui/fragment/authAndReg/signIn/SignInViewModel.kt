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

    fun signIn(username: String, password: String) =
        authenticationRepository.signIn(username, password).gatherRequest(_signInState)
}