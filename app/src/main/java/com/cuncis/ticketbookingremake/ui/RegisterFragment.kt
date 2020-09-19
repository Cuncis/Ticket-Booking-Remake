package com.cuncis.ticketbookingremake.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.cuncis.ticketbookingremake.R
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.layout_register_one.*


class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        btn_continue.setOnClickListener {
//            layout_one.visibility = View.GONE
//            layout_two.visibility = View.VISIBLE
//        }
    }
}