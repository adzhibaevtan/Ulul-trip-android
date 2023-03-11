package com.geeks.ulul.core.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.geeks.ulul.data.util.Either
import com.geeks.ulul.ui.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes layoutId: Int
) :
    Fragment(layoutId) {
    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        assembleViews()
        initListeners()
        initRequest()
        initSubscribers()
    }

    protected open fun initialize() {}

    protected open fun assembleViews() {}

    protected open fun initListeners() {}

    protected open fun initRequest() {}

    protected open fun initSubscribers() {}

    protected fun <T : Any> Flow<PagingData<T>>.spectatePaging(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: suspend (data: PagingData<T>) -> Unit,
    ) {
        safeFlowGather(lifecycleState) {
            collectLatest {
                success(it)
            }
        }
    }

    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
        gatherIfSucceed: ((state: UIState<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                gatherIfSucceed?.invoke(it)
                when (it) {
                    is UIState.Idle -> idle?.invoke(it)
                    is UIState.Loading -> loading?.invoke(it)
                    is UIState.Error -> error?.invoke(it.error)
                    is UIState.Success -> success?.invoke(it.data)
                }
            }
        }
    }

    fun safeFlowGather(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }

    fun <T> Flow<Either<String, T>>.safeFlowGather(
        actionIfEitherIsRight: suspend (T) -> Unit,
        actionIfEitherIsLeft: (error: String) -> Unit,
    ) {
        safeFlowGather {
            collect {
                when (it) {
                    is Either.Right -> actionIfEitherIsRight(it.value)
                    is Either.Left -> actionIfEitherIsLeft(it.value)
                }
            }
        }
    }

    protected fun <T> UIState<T>.assembleViewVisibility(
        group: Group,
        loader: ProgressBar,
        navigationSucceed: Boolean = false,
    ) {
        fun displayLoader(isDisplayed: Boolean) {
            group.isVisible = !isDisplayed
            loader.isVisible = isDisplayed
        }
        when (this) {
            is UIState.Idle -> {}
            is UIState.Loading -> displayLoader(true)
            is UIState.Error -> displayLoader(false)
            is UIState.Success -> {
                if (navigationSucceed) {
                    displayLoader(true)
                } else {
                    displayLoader(false)
                }
            }
        }
    }

    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        uiState: ((UIState<T>) -> Unit)? = null,
        onLoading: (() -> Unit?)? = null,
        onSuccess: (data: T) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectUIState.collect { state ->
                    uiState?.invoke(state)
                    when (state) {
                        is UIState.Idle -> {}
                        is UIState.Error -> {}
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            onSuccess(state.data)
                        }
                    }
                }
            }
        }
    }
}