package com.example.userprofileapp.data.remote

import com.example.userprofileapp.data.remote.model.UserDto
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
