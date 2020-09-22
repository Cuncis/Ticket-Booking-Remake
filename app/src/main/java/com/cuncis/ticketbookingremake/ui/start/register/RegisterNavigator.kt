package com.cuncis.ticketbookingremake.ui.start.register

import com.google.firebase.firestore.QuerySnapshot

interface RegisterNavigator {
    fun onSuccess(query: QuerySnapshot)
    fun onError(message: String)
    fun onLoading(loading: Boolean)
}