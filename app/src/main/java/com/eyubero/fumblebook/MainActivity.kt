package com.eyubero.fumblebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eyubero.fumblebook.ui.screen.NewUserScreen
import com.eyubero.fumblebook.ui.theme.FumbleBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FumbleBookTheme {
                navigateTo()
            }
        }
    }
}

@Composable
fun navigateTo() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen", builder = {
        composable("login_screen", content = { NewUserScreen(navController = navController) })
    })
}

