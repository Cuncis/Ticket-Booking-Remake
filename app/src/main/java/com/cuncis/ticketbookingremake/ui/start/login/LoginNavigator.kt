package com.cuncis.ticketbookingremake.ui.start.login

import com.google.firebase.firestore.QuerySnapshot


interface LoginNavigator {
    fun goToMain()
    fun goToRegister()
    fun onSuccess(query: QuerySnapshot)
    fun onError(message: String)
}