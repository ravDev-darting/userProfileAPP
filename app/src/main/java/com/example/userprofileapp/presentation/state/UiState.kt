package com.example.userprofileapp.presentation.state

import com.example.userprofileapp.domain.model.User

sealed class UiState {
    object Loading : UiState()
    data class Success(val user: User) : UiState()
    data class Error(val message: String) : UiState()
}
