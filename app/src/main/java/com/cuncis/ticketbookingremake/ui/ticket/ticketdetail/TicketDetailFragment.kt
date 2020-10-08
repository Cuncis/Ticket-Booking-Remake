package com.cuncis.ticketbookingremake.ui.ticket.ticketdetail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.data.Travel
import com.cuncis.ticketbookingremake.databinding.FragmentTicketDetailBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.CustomProgressDialog
import com.cuncis.ticketbookingremake.util.Status
import com.cuncis.ticketbookingremake.util.showLog
import com.google.firebase.firestore.ktx.toObject


class TicketDetailFragment : BaseFragment<FragmentTicketDetailBinding, TicketDetailViewModel>(),
    TicketDetailNavigator {

    private val _viewModel by viewModels<TicketDetailViewModel>()
    private lateinit var binding: FragmentTicketDetailBinding

    private val progressDialog by lazy { CustomProgressDialog(requireContext()) }

    private val travelNameKey: String by lazy { arguments?.getString("key").toString() }

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.vm = _viewModel
        _viewModel.navigator = this
    }

    override fun setLayout() = R.layout.fragment_ticket_detail

    override fun getViewModel() = _viewModel

    override fun onReadyAction() {
        _viewModel.showTicketDetail(travelNameKey)
    }

    override fun onObserveAction() {
        _viewModel.ticketDetail.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.hide()
                    it.data?.let { query ->
                        for (data in query) {
                            val travel = data.toObject<Travel.Place>()
                            binding.travel = travel
                            showLog("$travel")
                        }
                    }
                }
                Status.ERROR -> {
                    progressDialog.hide()
                    showLog(it.message.toString())
                }
                Status.LOADING -> {
                    progressDialog.show()
                }
            }
        })
    }

    override fun goToCheckout() {
        findNavController().navigate(R.id.action_ticketDetailFragment_to_ticketCheckoutFragment)
    }

    override fun goToBack() {
        requireActivity().onBackPressed()
    }
}