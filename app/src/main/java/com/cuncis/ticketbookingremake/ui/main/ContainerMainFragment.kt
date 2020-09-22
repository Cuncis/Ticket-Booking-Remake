package com.cuncis.ticketbookingremake.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.ui.base.ContainerBaseFragment


class ContainerMainFragment : ContainerBaseFragment() {

    override fun setLayout() = R.layout.fragment_container_main

    override fun onInitialization() {
        super.onInitialization()
        onStartDestination()
    }

    private fun onStartDestination() {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_main_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_main)

        when (arguments?.getString("to")) {
            "dashboard" -> {
                navGraph.startDestination = R.id.dashboardFragment
                navController.graph = navGraph
            }
        }
    }

}