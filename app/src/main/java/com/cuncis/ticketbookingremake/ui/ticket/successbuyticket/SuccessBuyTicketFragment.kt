package com.cuncis.ticketbookingremake.ui.ticket.successbuyticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import kotlinx.android.synthetic.main.fragment_success_buy_ticket.*


class SuccessBuyTicketFragment : Fragment(R.layout.fragment_success_buy_ticket) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_view_ticket.setOnClickListener {
            findNavController().navigate(R.id.action_successBuyTicketFragment_to_containerMainFragment)
        }
    }


}