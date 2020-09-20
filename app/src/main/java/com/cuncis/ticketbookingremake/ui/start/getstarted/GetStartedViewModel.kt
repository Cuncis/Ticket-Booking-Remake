package com.cuncis.ticketbookingremake.ui.start.getstarted

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class GetStartedViewModel @ViewModelInject constructor() : ViewModel() {

    private lateinit var navigator: GetStartedNavigator

    fun setNavigator(navigator: GetStartedNavigator) {
        this.navigator = navigator
    }

    fun getNavigator() = navigator
}