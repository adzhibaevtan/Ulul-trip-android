package com.geeks.ulul.ui.fragment.main.home


import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel by viewModels<HomeViewModel>()
}