package com.example.userprofileapp.domain.model.usecase

import com.example.userprofileapp.domain.model.User
import com.example.userprofileapp.domain.model.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserUseCase(
    private val repository: UserRepository
) {
    // Return the first user from the list
    operator fun invoke(): Flow<User> = repository.getUsers().map { it.first() }
}
