package com.cuncis.ticketbookingremake.ui.start.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class RegisterViewModel @ViewModelInject constructor() : BaseViewModel<RegisterNavigator>() {

    private val userCollectionPref = Firebase.firestore.collection("User")

    private val _register = MutableLiveData<Resource<User>>()
    val register: LiveData<Resource<User>>
        get() = _register

    private val _checkUser = MutableLiveData<Resource<Boolean>>()
    val checkUser: LiveData<Resource<Boolean>>
        get() = _checkUser

    fun register(user: User) = CoroutineScope(Dispatchers.IO).launch {
        _register.postValue(Resource.loading(null))
        try {
            userCollectionPref.add(user).await()
            _register.postValue(Resource.success(user))
        } catch (e: Exception) {
            _register.postValue(Resource.error(e.message.toString(), null))
        }
    }

    fun checkUsername(username: String) = viewModelScope.launch {
        _checkUser.postValue(Resource.loading(null))
        try {
            val query = userCollectionPref
                .whereEqualTo("username", username)
                .get()
                .await()

            if (query.size() == 1) {
                _checkUser.postValue(Resource.success(true))
            } else {
                _checkUser.postValue(Resource.success(false))
            }
        } catch (e: Exception) {
            _checkUser.postValue(Resource.error(e.message.toString(), null))
        }
    }
}