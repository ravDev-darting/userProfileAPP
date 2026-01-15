package com.example.userprofileapp.domain.model.repository

import com.example.userprofileapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
}
