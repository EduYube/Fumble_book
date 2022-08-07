package com.eyubero.fumblebook.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Users(
    val data: User,
    val _id: String,
    val __v: Int
)

@JsonClass(generateAdapter = true)
data class User (
    val email: String,
    var first_name: String,
    val last_name: String?,
    val alias: String,
    val password: String
        )