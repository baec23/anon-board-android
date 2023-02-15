package com.baec23.composetemplate.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.navigation.navgraph.navigateToMainNavGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navService: NavService,
) : ViewModel() {

    private val _username: MutableStateFlow<String> = MutableStateFlow("")
    val username = _username.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            LoginUiEvent.LoginButtonPressed -> navService.navigateToMainNavGraph()
            is LoginUiEvent.UsernameChanged -> _username.value = event.username
        }
    }
}

sealed class LoginUiEvent {
    data class UsernameChanged(val username: String) : LoginUiEvent()
    object LoginButtonPressed : LoginUiEvent()
}