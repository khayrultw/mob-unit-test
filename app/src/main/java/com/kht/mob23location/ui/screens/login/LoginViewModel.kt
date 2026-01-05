package com.kht.mob23location.ui.screens.login

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kht.mob23location.data.repo.IUserRepo
import com.kht.mob23location.data.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: IUserRepo
): ViewModel() {
    private val _finish = MutableSharedFlow<Unit>()
    val finish = _finish.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _greetings = MutableStateFlow("")
    val greetings = _greetings.asStateFlow()

    fun greet(name: String) {
        viewModelScope.launch {
            delay(1000)
            _greetings.value = "Hello $name"
        }
    }

    fun greetings(): String {
        return "Hello ${fetchUser()}"
    }

    fun fetchUser(): String {
        val user = repo.getUser()
        _greetings.value = "Hello $user"
        return user
    }

    fun login(email: String, pass: String) {
        val vRes = validate(email, pass)
        if(vRes != null) {
            viewModelScope.launch {
                _error.emit(vRes)
            }
            return
        }

        viewModelScope.launch {
            _finish.emit(Unit)
        }
    }

    fun validate(email: String, pass: String): String? {
        return try {
            require(email.isNotBlank() && email == "email@a.com") { "Invalid Email" }
            require(pass.isNotBlank() && pass == "password") {"Invalid Password"}
            null
        } catch (e: Exception) {
            e.message.toString()
        }
    }
}