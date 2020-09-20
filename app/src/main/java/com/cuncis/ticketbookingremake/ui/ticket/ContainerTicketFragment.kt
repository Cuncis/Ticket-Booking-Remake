package com.cuncis.ticketbookingremake.ui.ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.cuncis.ticketbookingremake.R


class ContainerTicketFragment : Fragment(R.layout.fragment_container_ticket) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onStartDestination()
    }

    private fun onStartDestination() {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_ticket_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_ticket)

        when (arguments?.getString("to")) {
            "dashboard" -> {
                navGraph.startDestination = R.id.dashboardFragment
                navController.graph = navGraph
            }
        }
    }

}