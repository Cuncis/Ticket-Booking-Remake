package com.cuncis.ticketbookingremake.ui.ticket.ticketcheckout

import androidx.fragment.app.viewModels
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.data.Ticket
import com.cuncis.ticketbookingremake.databinding.FragmentTicketCheckoutBinding
import com.cuncis.ticketbookingremake.ui.base.BaseFragment
import com.cuncis.ticketbookingremake.util.Status
import com.cuncis.ticketbookingremake.util.showLog


class TicketCheckoutFragment :
    BaseFragment<FragmentTicketCheckoutBinding, TicketCheckoutViewModel>(),
    TicketCheckoutNavigator {

    private val _viewModel by viewModels<TicketCheckoutViewModel>()
    private lateinit var binding: FragmentTicketCheckoutBinding

    override fun setLayout() = R.layout.fragment_ticket_checkout

    override fun getViewModel() = _viewModel

    override fun onInitialization() {
        binding = getViewDataBinding()
        binding.vm = _viewModel
        _viewModel.navigator = this
    }

    override fun onReadyAction() {
        _viewModel.ticket.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { ticket ->
                        showLog("$ticket")
                    }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        })
    }

    override fun goToTicketSuccess() {
//        findNavController().navigate(R.id.action_ticketCheckoutFragment_to_successBuyTicketFragment)
        _viewModel.buyTicket(
            "Monas",
            "cuncis3",
            Ticket("1111", travel_name = "Monas", location = "Jakarta", travel_date = "Yerterday")
        )
    }

    override fun goToBack() {
        requireActivity().onBackPressed()
    }
}