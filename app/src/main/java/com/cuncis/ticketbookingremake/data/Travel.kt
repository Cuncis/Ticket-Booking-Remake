package com.cuncis.ticketbookingremake.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Travel(
    val place: Place
) {
    data class Place(
        val travel_name: String = "",
        val location: String = "",
        val travel_date: String = "",
        val travel_time: String = "",
        val short_desc: String = "",
        val ticket_price: Long = 0L,
        val _festival: String = "",
        val _photo_spot: String = "",
        val _wifi: String = "",
        val privacy: String = "",
        val url_thumbnail: String = ""
    )
}