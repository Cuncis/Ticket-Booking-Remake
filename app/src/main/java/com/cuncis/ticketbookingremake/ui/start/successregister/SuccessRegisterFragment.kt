package com.cuncis.ticketbookingremake.ui.start.successregister

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentSuccessRegisterBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment


class SuccessRegisterFragment : BaseFragment<FragmentSuccessRegisterBinding, SuccessRegisterViewModel>(),
    SuccessRegisterNavigator {

    private val _viewModel by viewModels<SuccessRegisterViewModel>()

    private lateinit var binding: FragmentSuccessRegisterBinding

    private val args by navArgs<SuccessRegisterFragmentArgs>()

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.vm = _viewModel
        _viewModel.navigator = this
    }

    override fun onReadyAction() {
        super.onReadyAction()
        _viewModel.username = args.username
    }

    override fun setLayout() = R.layout.fragment_success_register

    override fun getViewModel() = _viewModel

    override fun goToMain() {
        findNavController().navigate(R.id.action_successRegisterFragment_to_containerMainFragment)
    }
}