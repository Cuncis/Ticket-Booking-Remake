package com.cuncis.ticketbookingremake.ui.ticket.ticketcheckout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import kotlinx.android.synthetic.main.fragment_ticket_checkout.*


class TicketCheckoutFragment : Fragment(R.layout.fragment_ticket_checkout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_buy_ticket.setOnClickListener {
            findNavController().navigate(R.id.action_ticketCheckoutFragment_to_successBuyTicketFragment)
        }

        btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}