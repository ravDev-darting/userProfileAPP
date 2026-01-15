package com.example.userprofileapp.presentation.state.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userprofileapp.data.remote.repository.UserRepositoryImpl

import com.example.userprofileapp.domain.model.usecase.GetUserUseCase
import com.example.userprofileapp.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    // Use case with repository
    private val useCase = GetUserUseCase(UserRepositoryImpl())

    // StateFlow to hold UI state
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            useCase()
                .catch { throwable ->
                    _state.value = UiState.Error(throwable.message ?: "Unknown error")
                }
                .collect { user ->
                    _state.value = UiState.Success(user)
                }
        }
    }
}
