package com.cuncis.ticketbookingremake.data

data class Ticket(
    val ticket_id: String = "",
    val travel_name: String = "",
    val location: String = "",
    val privacy: String = "",
    val ticket_total: Long = 0L,
    val travel_date: String = "",
    val travel_time: String = ""
)