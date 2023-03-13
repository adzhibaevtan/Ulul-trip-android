package com.geeks.ulul.ui.fragment.main.profile

import com.geeks.ulul.core.base.BaseViewModel
import com.geeks.ulul.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _accountDeletionState = mutableUiStateFlow<Unit>()
    val accountDeletionState = _accountDeletionState.asStateFlow()

    fun deleteAccount() = userRepository.deleteAccount().gatherRequest(_accountDeletionState)
}