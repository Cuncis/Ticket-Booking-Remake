package com.cuncis.ticketbookingremake.ui.start.getstarted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.cuncis.ticketbookingremake.R
import kotlinx.android.synthetic.main.fragment_get_started.*


class GetStartedFragment : Fragment(R.layout.fragment_get_started) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_sign_in.setOnClickListener {
            findNavController().navigate(R.id.action_getStartedFragment_to_containerMainFragment)    // sample, delete it later (frag to nav)
        }
    }
    
}