package com.cuncis.ticketbookingremake.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import kotlinx.coroutines.*


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val navController: NavController? by lazy { view?.findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                navController?.navigate(R.id.action_splashFragment_to_containerStartFragment)
            }
        }
    }
}