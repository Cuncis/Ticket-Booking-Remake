package com.cuncis.ticketbookingremake.ui.main.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_ticket_pisa.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_nav_ticket)   // sample, delete it later (frag to nav)
        }
    }
}