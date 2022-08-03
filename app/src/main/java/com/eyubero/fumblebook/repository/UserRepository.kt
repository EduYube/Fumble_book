package com.eyubero.fumblebook.repository

import android.util.Log
import com.eyubero.fumblebook.model.User
import com.eyubero.fumblebook.model.Users
import com.eyubero.fumblebook.provider.ApiProvider
import javax.inject.Inject

interface UserRepository {
    suspend fun createUser(body: Users)
}

class UserRepositoryImpl @Inject constructor(private val apiProvide: ApiProvider) : UserRepository {
    override suspend fun createUser(body: Users) {
        val response = apiProvide.createUser(
            User(
                "edu2@edu.com",
                "edu",
                "yubero",
                "kyriad",
                "123456"
            )
        )

        Log.d("<<<<<<<<<<",response.body().toString())
    }

}