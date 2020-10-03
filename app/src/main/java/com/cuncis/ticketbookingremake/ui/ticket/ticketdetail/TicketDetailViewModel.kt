package com.cuncis.ticketbookingremake.ui.ticket.ticketdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuncis.ticketbookingremake.data.Travel
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel
import com.cuncis.ticketbookingremake.util.Resource
import com.cuncis.ticketbookingremake.util.showLog
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TicketDetailViewModel @ViewModelInject constructor(): BaseViewModel<TicketDetailNavigator>() {

    private val travelPref = Firebase.firestore.collection("Travel")

    private val _ticketDetail = MutableLiveData<Resource<QuerySnapshot>>()
    val ticketDetail: LiveData<Resource<QuerySnapshot>>
        get() = _ticketDetail

    fun addSampleData(place: Travel.Place) = viewModelScope.launch {
        try {
            travelPref.add(place).await()
            showLog("Data Successfully Added")
        } catch (e: Exception) {
            showLog(e.message.toString())
        }
    }

    fun showTicketDetail(travelName: String) = viewModelScope.launch {
        _ticketDetail.postValue(Resource.loading(null))
        try {
            val query = travelPref
                .whereEqualTo("travel_name", travelName)
                .get()
                .await()
            _ticketDetail.postValue(Resource.success(query))
        } catch (e: Exception) {
            _ticketDetail.postValue(Resource.error(e.message.toString(), null))
        }
    }
}