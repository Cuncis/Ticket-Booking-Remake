package com.cuncis.ticketbookingremake.ui.start.getstarted

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentGetStartedBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GetStartedFragment : BaseFragment<FragmentGetStartedBinding, GetStartedViewModel>(),
    GetStartedNavigator {

    private val _viewModel by viewModels<GetStartedViewModel>()
    private lateinit var binding: FragmentGetStartedBinding

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.viewModel = _viewModel
        _viewModel.navigator = this
    }

    override fun getViewModel(): GetStartedViewModel = _viewModel

    override fun setLayout(): Int = R.layout.fragment_get_started

    override fun goToLogin() {
        findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
    }

    override fun goToRegister() {
        findNavController().navigate(R.id.action_getStartedFragment_to_registerFragment)
    }
}