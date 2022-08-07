package com.eyubero.fumblebook.repository

import com.eyubero.fumblebook.model.User
import com.eyubero.fumblebook.model.Users
import com.eyubero.fumblebook.provider.ApiProvider
import javax.inject.Inject

interface UserRepository {
    suspend fun createUser(body: User): Users
}

class UserRepositoryImpl @Inject constructor(private val apiProvider: ApiProvider) :
    UserRepository {
    override suspend fun createUser(user: User): Users {
        try {
            val response = apiProvider.createUser(
                body = user
            )
            return response.body()!!
        } catch (e: Error) {
            throw Exception()
        }
    }

}