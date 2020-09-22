package com.cuncis.ticketbookingremake.ui.start.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentRegisterBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.layout_register_two.view.*


class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(), RegisterNavigator {

    private val _viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: FragmentRegisterBinding

    private var isNextPage = false

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        _viewModel.navigator = this
        binding.nextPage = isNextPage
    }

    override fun onReadyAction() {
        binding.layoutOne.btnContinue.setOnClickListener {
            val username = binding.layoutOne.etUsername.text.toString()
            val password = binding.layoutOne.etPassword.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                _viewModel.register(username, password)
                isNextPage = true
                binding.nextPage = true
            } else {
                Toast.makeText(requireContext(), "Field cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.layoutTwo.btnContinue.setOnClickListener {
            Toast.makeText(requireContext(), "Success Register", Toast.LENGTH_SHORT).show()
        }
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

    override fun onSuccess(query: QuerySnapshot) {
        Toast.makeText(requireContext(), "Success Register", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(loading: Boolean) {
//        if (loading) {
//            binding.layoutOne.btnContinue.text = "Loading..."
//        } else {
//            binding.layoutOne.btnContinue.text = "Continue"
//        }
    }


}