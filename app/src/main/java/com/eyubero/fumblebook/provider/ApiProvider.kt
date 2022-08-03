package com.eyubero.fumblebook.provider

import com.eyubero.fumblebook.model.User
import com.eyubero.fumblebook.model.Users
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiProvider {

    @POST("/api/v1/users/create")
    suspend fun createUser(
        @Body body: User
    ): Response<Users>
}