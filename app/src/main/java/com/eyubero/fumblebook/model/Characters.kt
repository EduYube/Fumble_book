package com.eyubero.fumblebook.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Characters(
    val _id: String,
    val data: Character
)

data class Character(
    val name: String,
    val race: String?,
    val charclass: String?,
    val level: Int
)