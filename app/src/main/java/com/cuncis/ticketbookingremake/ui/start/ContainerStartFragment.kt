package com.cuncis.ticketbookingremake.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.cuncis.ticketbookingremake.R


class ContainerStartFragment : Fragment(R.layout.fragment_container_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onStartDestination()
    }

    private fun onStartDestination() {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_start_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_start)

        when (arguments?.getString("to")) {
            "getStarted" -> {
                navGraph.startDestination = R.id.getStartedFragment
                navController.graph = navGraph
            }
        }
    }

}