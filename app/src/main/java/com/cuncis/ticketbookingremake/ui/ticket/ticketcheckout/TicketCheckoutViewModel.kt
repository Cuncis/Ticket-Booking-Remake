package com.cuncis.ticketbookingremake.ui.ticket.ticketcheckout

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuncis.ticketbookingremake.data.Ticket
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel
import com.cuncis.ticketbookingremake.util.Resource
import com.cuncis.ticketbookingremake.util.showLog
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TicketCheckoutViewModel @ViewModelInject constructor() :
    BaseViewModel<TicketCheckoutNavigator>() {

    private val myTicketPref = Firebase.firestore.collection("MyTicket")
    private val travel = Firebase.firestore.collection("Travel")

    private val _ticket = MutableLiveData<Resource<Ticket>>()
    val ticket: LiveData<Resource<Ticket>>
        get() = _ticket

    fun buyTicket(key: String, username: String?, ticket: Ticket) = viewModelScope.launch {
        _ticket.postValue(Resource.loading(null))
        try {
            username?.let {
                val queryTravelName = myTicketPref.document(it).collection(key)
                    .whereEqualTo("travel_name", key)
                    .get()
                    .await()

                showLog("Check travel size: ${queryTravelName.size()}")
                val map = mapOf<String, Any>(
                    "ticket_id" to ticket.ticket_id,
                    "travel_name" to ticket.travel_name,
                    "location" to ticket.location,
                    "privacy" to ticket.privacy,
                    "ticket_total" to ticket.ticket_total,
                    "travel_date" to ticket.travel_date,
                    "travel_time" to ticket.travel_time
                )

                if (queryTravelName.size() == 1) {
                    for (document in queryTravelName) {
                        myTicketPref.document(it).collection(key).document(document.id).set(map, SetOptions.merge()).await()
                    }
                    _ticket.postValue(Resource.success(ticket))
                } else {
                    myTicketPref.document(it).collection(key).document().set(map).await()
                    _ticket.postValue(Resource.success(ticket))
                }
            }
        } catch (e: Exception) {
            _ticket.postValue(Resource.error(e.message.toString(), null))
        }
    }

    fun travelDetail() = viewModelScope.launch {
        try {

        } catch (e: Exception) {

        }
    }

    fun getBalance() = viewModelScope.launch {

    }
}