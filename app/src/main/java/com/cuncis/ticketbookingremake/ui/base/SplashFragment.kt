package com.cuncis.ticketbookingremake.ui.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.util.Constants.KEY_IS_LOGIN
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val navController: NavController? by lazy { view?.findNavController() }

    @Inject
    lateinit var pref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                if (pref.getBoolean(KEY_IS_LOGIN, false)) {
                    val bundle = bundleOf("to" to "main")
                    navController?.navigate(R.id.action_splashFragment_to_containerStartFragment, bundle)
                } else {
                    navController?.navigate(R.id.action_splashFragment_to_containerStartFragment)
                }
            }
        }
    }
}