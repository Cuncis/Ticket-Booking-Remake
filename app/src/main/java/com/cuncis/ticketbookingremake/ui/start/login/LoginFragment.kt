package com.cuncis.ticketbookingremake.ui.start.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentLoginBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.Status
import com.google.firebase.firestore.QuerySnapshot
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
        _viewModel.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
//        findNavController().navigate(R.id.action_loginFragment_to_containerMainFragment)
    }

    override fun onObserveAction() {
        _viewModel.login.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { query ->
                        if (query.size() == 1) {
                            Toast.makeText(requireContext(), "Success Login", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Success Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {

                }
            }
        })
    }

    override fun goToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}