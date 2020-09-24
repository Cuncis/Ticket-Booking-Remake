package com.cuncis.ticketbookingremake.ui.start.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel
import com.cuncis.ticketbookingremake.util.Resource
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class LoginViewModel @ViewModelInject constructor() : BaseViewModel<LoginNavigator>() {

    private val userCollectionPref = Firebase.firestore.collection("User")

    private val _login = MutableLiveData<Resource<QuerySnapshot>>()
    val login: LiveData<Resource<QuerySnapshot>>
        get() = _login

    fun login(username: String, password: String) = CoroutineScope(Dispatchers.IO).launch {
        _login.postValue(Resource.loading(null))
        try {
            val query = userCollectionPref
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .await()

            _login.postValue(Resource.success(query))
        } catch (e: Exception) {
            _login.postValue(Resource.error(e.message.toString(), null))
        }
    }
}