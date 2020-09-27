package com.cuncis.ticketbookingremake.ui.main.dashboard

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentDashboardBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.Constants.KEY_IS_LOGIN
import com.cuncis.ticketbookingremake.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(), DashboardNavigator {

    private val _viewModel by viewModels<DashboardViewModel>()
    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var pref: SharedPreferences

    override fun setLayout() = R.layout.fragment_dashboard

    override fun getViewModel() = _viewModel

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.vm = _viewModel
        _viewModel.navigator = this
    }

    override fun onReadyAction() {
        requireContext().toast("Is Login ${pref.getBoolean(KEY_IS_LOGIN, false)}")
        pref.edit().putBoolean(KEY_IS_LOGIN, false).apply()

        btn_ticket_pisa.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_containerTicketFragment)   // sample, delete it later (frag to nav)
        }
    }

    override fun goToProfile() {
        TODO("Not yet implemented")
    }
}