package com.cuncis.ticketbookingremake.data

data class User(
    val username: String,
    val password: String,
    val email: String = "",
    val fullName: String = "",
    val passion: String = "",
    val imageUrl: String = ""
)