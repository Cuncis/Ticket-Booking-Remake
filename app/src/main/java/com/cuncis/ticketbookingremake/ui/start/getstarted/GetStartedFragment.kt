package com.cuncis.ticketbookingremake.ui.start.getstarted

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import com.cuncis.ticketbookingremake.databinding.FragmentGetStartedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_get_started.*
import javax.inject.Inject


@AndroidEntryPoint
class GetStartedFragment : Fragment(), GetStartedNavigator {

    private val viewModel by viewModels<GetStartedViewModel>()

    private lateinit var binding: FragmentGetStartedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_get_started,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
        binding.viewModel = viewModel
    }

    override fun goToLogin() {
        findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
    }

    override fun goToRegister() {
        findNavController().navigate(R.id.action_getStartedFragment_to_registerFragment)
    }
}