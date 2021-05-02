package com.example.starwars.model

data class PersonsResponse(
    val count: Int?,
    val nextUrl: String?,
    val persons: ArrayList<Person>
)