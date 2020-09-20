package com.cuncis.ticketbookingremake.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.cuncis.ticketbookingremake.R


class ContainerMainFragment : Fragment(R.layout.fragment_container_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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