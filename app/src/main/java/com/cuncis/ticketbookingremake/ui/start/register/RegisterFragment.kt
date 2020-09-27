package com.cuncis.ticketbookingremake.ui.start.register

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.databinding.FragmentRegisterBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.*
import com.cuncis.ticketbookingremake.util.Constants.KEY_USERNAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(),
    RegisterNavigator {
    companion object {
        const val PHOTO_CODE = 1
    }

    private val _viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: FragmentRegisterBinding
    private var photoLocation: Uri? = null

    private var username: String = ""
    private var password: String = ""
    private var email: String = ""
    private var fullName: String = ""
    private var passion: String = ""
    private var isNextPage = false

    private val progressDialog by lazy { CustomProgressDialog(requireContext()) }

    @Inject
    lateinit var pref: SharedPreferences

    override fun setLayout() = R.layout.fragment_register

    override fun getViewModel() = _viewModel

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.vm = _viewModel
        _viewModel.navigator = this
        binding.nextPage = isNextPage
    }

    override fun onReadyAction() {
        binding.layoutOne.btnBack.setOnClickListener { onBackStack() }
        binding.layoutTwo.btnBack.setOnClickListener { onBackStack() }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { onBackStack() }
        binding.layoutTwo.btnAddPhoto.setOnClickListener { findPhoto() }
    }

    override fun onObserveAction() {
        _viewModel.checkUser.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.hide()
                    it.data?.let { isExist ->
                        if (isExist) {
                            Toast.makeText(requireContext(), "User is exist", Toast.LENGTH_SHORT)
                                .show()
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
                        val directions =
                            RegisterFragmentDirections.actionRegisterFragmentToSuccessRegisterFragment(
                                user.username
                            )
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
        _viewModel.uploadPhoto.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.hide()
                    it.data?.let { uri ->
                        _viewModel.register(
                            User(
                                username,
                                password,
                                email,
                                fullName,
                                passion,
                                uri.toString()
                            )
                        )
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

    private fun findPhoto() {
        val pic = Intent()
        pic.type = "image/*"
        pic.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(pic, PHOTO_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PHOTO_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let {
                photoLocation = it
                Glide.with(this).load(photoLocation).centerCrop()
                    .into(binding.layoutTwo.picPhotoRegisterUser)
            }
        }
    }

    private fun onBackStack() {
        pref.edit().putString(KEY_USERNAME, "").apply()
        if (isNextPage) {
            isNextPage = false
            binding.nextPage = false
        } else {
            findNavController().popBackStack()
        }
    }

    override fun goToRegisterTwo() {
        username = binding.layoutOne.etUsername.text.toString()
        password = binding.layoutOne.etPassword.text.toString()
        email = binding.layoutOne.etEmailAddress.text.toString()
        when {
            username.isEmpty() -> {
                binding.layoutOne.etUsername.error = "Field cannot be empty"
            }
            password.isEmpty() -> {
                binding.layoutOne.etPassword.error = "Field cannot be empty"
            }
            email.isEmpty() -> {
                binding.layoutOne.etEmailAddress.error = "Field cannot be empty"
            }
            else -> {
                if (isValidEmail(email)) {
                    pref.edit().putString(KEY_USERNAME, username).apply()
                    _viewModel.checkUsername(username)
                } else {
                    binding.layoutOne.etEmailAddress.error = "Email invalid"
                }
            }
        }
    }

    override fun goToRegisterSuccess() {
        fullName = binding.layoutTwo.etNamaLengkap.text.toString()
        passion = binding.layoutTwo.etBio.text.toString()
        when {
            fullName.isEmpty() -> {
                binding.layoutTwo.etNamaLengkap.error = "Field is required"
            }
            passion.isEmpty() -> {
                binding.layoutTwo.etBio.error = "Field is required"
            }
            else -> {
                photoLocation?.let {
                    _viewModel.uploadPhotoUser(
                        requireContext().getFileExtension(it),
                        it,
                        pref.getString(KEY_USERNAME, "").toString()
                    )
                } ?: run {
                    requireContext().toast("Photo is required!")
                }
            }
        }
    }
}