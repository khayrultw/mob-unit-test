package com.kht.mob23location.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kht.mob23location.ui.screens.home.HomeScreen
import com.kht.mob23location.ui.screens.login.LoginScreen
import com.kht.mob23location.ui.screens.register.RegisterScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login
    ) {
        composable<Screen.Home> {
            HomeScreen()
        }

        composable<Screen.Login> {
            LoginScreen(navController)
        }

        composable<Screen.Register> {
            RegisterScreen(navController)
        }
    }
}