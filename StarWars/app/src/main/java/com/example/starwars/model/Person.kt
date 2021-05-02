package com.example.starwars.model

data class Person(
    var name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val created: String,
    val edited: String,
    val url: String
) {
    override fun toString(): String {
        return name
    }
}
