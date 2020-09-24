package com.cuncis.ticketbookingremake.ui.start.successregister

import androidx.hilt.lifecycle.ViewModelInject
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel

class SuccessRegisterViewModel @ViewModelInject constructor()
    : BaseViewModel<SuccessRegisterNavigator>() {

    var username: String = ""
}