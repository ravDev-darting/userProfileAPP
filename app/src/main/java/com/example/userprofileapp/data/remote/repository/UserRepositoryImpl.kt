package com.example.userprofileapp.data.remote.repository

import com.example.userprofileapp.data.remote.RetrofitClient
import com.example.userprofileapp.data.remote.mapper.toDomain
import com.example.userprofileapp.domain.model.User
import com.example.userprofileapp.domain.model.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl : UserRepository {

    override fun getUsers(): Flow<List<User>> = flow {
        try {
            val users = RetrofitClient.apiService.getUsers().map { it.toDomain() }
            emit(users)
        } catch (e: Exception) {
            throw e
        }
    }
}
