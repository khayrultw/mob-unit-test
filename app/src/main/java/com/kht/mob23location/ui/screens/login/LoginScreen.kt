package com.kht.mob23location.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.kht.mob23location.ui.navigation.Screen
import com.kht.mob23location.ui.screens.register.EmailPassAuth

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.finish.collect {
            navController.navigate(Screen.Home) {
                popUpTo(Screen.Login) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.error.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        EmailPassAuth(
            modifier = Modifier.fillMaxWidth(),
            title = "Login",
            actionButtonText = "Login",
            action = { email, pass -> viewModel.login(email, pass) }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Don't have an account yet?")
                TextButton(
                    onClick = { navController.navigate(Screen.Register) }
                ) {
                    Text("Register")
                }
            }
        }
    }
}