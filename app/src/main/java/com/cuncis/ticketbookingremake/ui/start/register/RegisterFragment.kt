package com.cuncis.ticketbookingremake.ui.start.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.databinding.FragmentRegisterBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.CustomProgressDialog
import com.cuncis.ticketbookingremake.util.Status
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.layout_register_two.view.*


class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(),
    RegisterNavigator {

    private val _viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: FragmentRegisterBinding

    private var username: String = ""
    private var password: String = ""
    private var email: String = ""
    private var isNextPage = false

    private val progressDialog by lazy { CustomProgressDialog(requireContext()) }

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.vm = _viewModel
        _viewModel.navigator = this
        binding.nextPage = isNextPage
    }

    override fun onReadyAction() {
        binding.layoutOne.btnBack.setOnClickListener {
            onBackStack()
        }
        binding.layoutTwo.btnBack.setOnClickListener {
            onBackStack()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onBackStack()
        }
    }

    override fun onObserveAction() {
        _viewModel.checkUser.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.hide()
                    it.data?.let { isExist ->
                        if (isExist) {
                            Toast.makeText(requireContext(), "User is exist", Toast.LENGTH_SHORT).show()
                            isNextPage = false
                            binding.nextPage = false
                        } else {
                            isNextPage = true
                            binding.nextPage = true
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
        _viewModel.register.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.hide()
                    it.data?.let { user ->
                        Toast.makeText(
                            requireContext(),
                            "Successfully Register",
                            Toast.LENGTH_SHORT
                        ).show()
                        val directions = RegisterFragmentDirections.actionRegisterFragmentToSuccessRegisterFragment(user.username)
                        findNavController().navigate(directions)
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

    private fun onBackStack() {
        if (isNextPage) {
            isNextPage = false
            binding.nextPage = false
        } else {
            findNavController().popBackStack()
        }
    }

    override fun setLayout() = R.layout.fragment_register

    override fun getViewModel() = _viewModel

    override fun goToRegisterTwo() {
        username = binding.layoutOne.etUsername.text.toString()
        password = binding.layoutOne.etPassword.text.toString()
        email = binding.layoutOne.etEmailAddress.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
            _viewModel.checkUsername(username)
        } else {
            Toast.makeText(requireContext(), "Field cannot be empty!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun goToRegisterSuccess() {
        val fullName = binding.layoutTwo.etNamaLengkap.text.toString()
        val passion = binding.layoutTwo.etBio.text.toString()
        val imageUrl = "url"
        _viewModel.register(User(
            username,
            password,
            email,
            fullName,
            passion,
            imageUrl
        ))
    }

}