package com.cuncis.ticketbookingremake.ui.start.login

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentLoginBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginNavigator {

    private val _viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.viewModel = _viewModel
        _viewModel.navigator = this
    }

    override fun setLayout(): Int = R.layout.fragment_login

    override fun getViewModel(): LoginViewModel = _viewModel

    override fun goToMain() {
        findNavController().navigate(R.id.action_loginFragment_to_containerMainFragment)
    }

    override fun goToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

}