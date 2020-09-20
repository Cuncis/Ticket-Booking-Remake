package com.cuncis.ticketbookingremake.ui.start.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel


class LoginViewModel @ViewModelInject constructor() : ViewModel() {

    private lateinit var navigator: LoginNavigator

    fun setNavigator(navigator: LoginNavigator) {
        this.navigator = navigator
    }

    fun getNavigator() = navigator
}