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

    private val useCase = GetUserUseCase(UserRepositoryImpl())

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            useCase()
                .catch { throwable ->
                    _state.value =
                        UiState.Error(throwable.message ?: "Unknown error")
                }
                .collect { users ->
                    _state.value = UiState.Success(users)
                }
        }
    }
}
