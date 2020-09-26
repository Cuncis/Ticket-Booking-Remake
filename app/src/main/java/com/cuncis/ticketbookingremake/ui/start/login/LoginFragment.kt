package com.cuncis.ticketbookingremake.ui.start.login

import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentLoginBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.Constants.KEY_IS_LOGIN
import com.cuncis.ticketbookingremake.util.CustomProgressDialog
import com.cuncis.ticketbookingremake.util.Status
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginNavigator {

    private val _viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding

    private val progressDialog by lazy { CustomProgressDialog(requireContext()) }

    @Inject
    lateinit var pref: SharedPreferences

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.viewModel = _viewModel
        _viewModel.navigator = this
    }

    override fun setLayout(): Int = R.layout.fragment_login

    override fun getViewModel(): LoginViewModel = _viewModel

    override fun goToMain() {
        when {
            binding.etUsername.text.toString().isEmpty() -> {
                binding.etUsername.error = "Field cannot be empty"
            }
            binding.etPassword.text.toString().isEmpty() -> {
                binding.etPassword.error = "Field cannot be empty"
            }
            else -> {
                _viewModel.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
            }
        }
    }

    override fun onObserveAction() {
        _viewModel.login.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { query ->
                        progressDialog.hide()
                        if (query.size() == 1) {
                            Toast.makeText(requireContext(), "Success Login", Toast.LENGTH_SHORT)
                                .show()
                            findNavController().navigate(R.id.action_loginFragment_to_containerMainFragment)
                    //        pref.edit().putBoolean(KEY_IS_LOGIN, true).apply()
                        } else {
                            Toast.makeText(requireContext(), "Success Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                Status.ERROR -> {
                    progressDialog.hide()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    progressDialog.show()
                }
            }
        })
    }

    override fun goToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}