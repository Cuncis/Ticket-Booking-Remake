package com.cuncis.ticketbookingremake.ui.main.dashboard

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.databinding.FragmentDashboardBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.Constants.KEY_IS_LOGIN
import com.cuncis.ticketbookingremake.util.Constants.KEY_USERNAME
import com.cuncis.ticketbookingremake.util.CustomProgressDialog
import com.cuncis.ticketbookingremake.util.Status
import com.cuncis.ticketbookingremake.util.toast
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(),
    DashboardNavigator {

    companion object {
        const val PISA = "Pisa"
        const val TORRI = "Torri"
        const val PAGODA = "Pagoda"
        const val CANDI = "Candi"
        const val SPHINX = "Sphinx"
        const val MONAS = "Monas"
    }

    private val _viewModel by viewModels<DashboardViewModel>()
    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var pref: SharedPreferences

    private val progressDialog by lazy { CustomProgressDialog(requireContext()) }

    override fun setLayout() = R.layout.fragment_dashboard

    override fun getViewModel() = _viewModel

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.vm = _viewModel
        _viewModel.navigator = this
    }

    override fun onReadyAction() {
        _viewModel.showUserInfo(pref.getString(KEY_USERNAME, "").toString())
        requireContext().toast("Is Login ${pref.getBoolean(KEY_IS_LOGIN, false)}")
//        pref.edit().putBoolean(KEY_IS_LOGIN, false).apply()
    }

    override fun onObserveAction() {
        _viewModel.userInfo.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.hide()
                    it.data?.let { query ->
                        for (data in query) {
                            val user = data.toObject<User>()
                            binding.user = user
                        }
                    }
                }
                Status.ERROR -> {
                    progressDialog.hide()
                    requireContext().toast(it.message.toString())
                }
                Status.LOADING -> {
                    progressDialog.show()
                }
            }
        })
    }

    override fun goToProfile() {
        findNavController().navigate(R.id.action_dashboardFragment_to_myProfileFragment)
    }

    override fun goToPisa() {
        val bundle = bundleOf("to" to PISA)
        findNavController().navigate(R.id.action_dashboardFragment_to_containerTicketFragment, bundle)   // sample, delete it later (frag to nav)
    }

    override fun goToTorri() {
        val bundle = bundleOf("to" to TORRI)
        findNavController().navigate(R.id.action_dashboardFragment_to_containerTicketFragment, bundle)
    }

    override fun goToPagoda() {
        val bundle = bundleOf("to" to PAGODA)
        findNavController().navigate(R.id.action_dashboardFragment_to_containerTicketFragment, bundle)
    }

    override fun goToCandi() {
        val bundle = bundleOf("to" to CANDI)
        findNavController().navigate(R.id.action_dashboardFragment_to_containerTicketFragment, bundle)
    }

    override fun goToSphinx() {
        val bundle = bundleOf("to" to SPHINX)
        findNavController().navigate(R.id.action_dashboardFragment_to_containerTicketFragment, bundle)
    }

    override fun goToMonas() {
        val bundle = bundleOf("to" to MONAS)
        findNavController().navigate(R.id.action_dashboardFragment_to_containerTicketFragment, bundle)
    }
}