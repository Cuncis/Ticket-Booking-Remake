package com.cuncis.ticketbookingremake.ui.ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.ui.base.ContainerBaseFragment
import com.cuncis.ticketbookingremake.ui.main.dashboard.DashboardFragment.Companion.CANDI
import com.cuncis.ticketbookingremake.ui.main.dashboard.DashboardFragment.Companion.MONAS
import com.cuncis.ticketbookingremake.ui.main.dashboard.DashboardFragment.Companion.PAGODA
import com.cuncis.ticketbookingremake.ui.main.dashboard.DashboardFragment.Companion.PISA
import com.cuncis.ticketbookingremake.ui.main.dashboard.DashboardFragment.Companion.SPHINX
import com.cuncis.ticketbookingremake.ui.main.dashboard.DashboardFragment.Companion.TORRI


class ContainerTicketFragment : ContainerBaseFragment() {

    override fun setLayout() = R.layout.fragment_container_ticket

    override fun onInitialization() {
        super.onInitialization()
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
            PISA -> {
                val bundle = bundleOf("key" to PISA)
                navGraph.startDestination = R.id.ticketDetailFragment
                navController.setGraph(navGraph, bundle)
            }
            TORRI -> {
                val bundle = bundleOf("key" to TORRI)
                navGraph.startDestination = R.id.ticketDetailFragment
                navController.setGraph(navGraph, bundle)
            }
            PAGODA -> {
                val bundle = bundleOf("key" to PAGODA)
                navGraph.startDestination = R.id.ticketDetailFragment
                navController.setGraph(navGraph, bundle)
            }
            CANDI -> {
                val bundle = bundleOf("key" to CANDI)
                navGraph.startDestination = R.id.ticketDetailFragment
                navController.setGraph(navGraph, bundle)
            }
            SPHINX -> {
                val bundle = bundleOf("key" to SPHINX)
                navGraph.startDestination = R.id.ticketDetailFragment
                navController.setGraph(navGraph, bundle)
            }
            MONAS -> {
                val bundle = bundleOf("key" to MONAS)
                navGraph.startDestination = R.id.ticketDetailFragment
                navController.setGraph(navGraph, bundle)
            }
        }
    }

}