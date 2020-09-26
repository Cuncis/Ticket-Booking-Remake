package com.cuncis.ticketbookingremake.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.ui.base.ContainerBaseFragment


class ContainerStartFragment : ContainerBaseFragment() {

    override fun setLayout() = R.layout.fragment_container_start

    override fun onInitialization() {
        super.onInitialization()
        onStartDestination()
    }

    private fun onStartDestination() {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_start_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_start)

        when (arguments?.getString("to")) {
            "main" -> {
                navGraph.startDestination = R.id.containerMainFragment
                navController.graph = navGraph
            }
        }
    }

}