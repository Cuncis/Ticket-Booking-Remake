package com.cuncis.ticketbookingremake.ui.main.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel
import com.cuncis.ticketbookingremake.util.Resource
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DashboardViewModel @ViewModelInject constructor() : BaseViewModel<DashboardNavigator>() {

    private val userCollectionPref = Firebase.firestore.collection("User")

    private val _userInfo = MutableLiveData<Resource<QuerySnapshot>>()
    val userInfo: LiveData<Resource<QuerySnapshot>>
        get() = _userInfo

    fun showUserInfo(username: String) = viewModelScope.launch {
        _userInfo.postValue(Resource.loading(null))
        try {
            val query = userCollectionPref
                .whereEqualTo("username", username)
                .get()
                .await()
            _userInfo.postValue(Resource.success(query))
        } catch (e: Exception) {
            _userInfo.postValue(Resource.error(e.message.toString(), null))
        }
    }

}