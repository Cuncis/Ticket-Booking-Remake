package com.cuncis.ticketbookingremake.ui.start.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterViewModel @ViewModelInject constructor() : BaseViewModel<RegisterNavigator>() {

    private val userCollectionPref = Firebase.firestore.collection("User")

    fun register(username: String, password: String) = CoroutineScope(Dispatchers.IO).launch {
        navigator?.onLoading(true)
        try {
            val query = userCollectionPref
                .whereEqualTo("username", username)
                .get()
                .await()

            if (query.size() == 1) {
                navigator?.onLoading(false)
                withContext(Dispatchers.Main) {
                    navigator?.onError("User is Exist")
                }
                return@launch
            }
            userCollectionPref.add(User(username, password)).await()
            withContext(Dispatchers.Main) {
                navigator?.onLoading(false)
                navigator?.onSuccess(query)
            }
        } catch (e: Exception) {
            navigator?.onLoading(false)
            withContext(Dispatchers.Main) {
                navigator?.onError(e.message.toString())
            }
        }
    }
}