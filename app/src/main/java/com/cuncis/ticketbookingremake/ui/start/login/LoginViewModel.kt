package com.cuncis.ticketbookingremake.ui.start.login

import androidx.hilt.lifecycle.ViewModelInject
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class LoginViewModel @ViewModelInject constructor() : BaseViewModel<LoginNavigator>() {

    private val userCollectionPref = Firebase.firestore.collection("User")

    fun login(username: String, password: String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val query = userCollectionPref
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .await()
            withContext(Dispatchers.Main) {
                navigator?.onSuccess(query)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                navigator?.onError(e.message.toString())
            }
        }
    }
}