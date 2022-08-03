package com.eyubero.fumblebook.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Users(
    val _id: String,
    val data: User
)

data class User (
    val email: String,
    val first_name: String,
    val last_name: String?,
    val alias: String,
    val password: String
        )