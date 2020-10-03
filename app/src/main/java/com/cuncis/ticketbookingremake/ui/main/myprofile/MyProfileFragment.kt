package com.cuncis.ticketbookingremake.ui.main.myprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentMyProfileBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment


class MyProfileFragment : BaseFragment<FragmentMyProfileBinding, MyProfileViewModel>(), MyProfileNavigator {

    private val _viewModel by viewModels<MyProfileViewModel>()
    private lateinit var binding: FragmentMyProfileBinding

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        _viewModel.navigator = this
    }

    override fun setLayout() = R.layout.fragment_my_profile

    override fun getViewModel() = _viewModel

    override fun goToEditProfile() {
        TODO("Not yet implemented")
    }
}