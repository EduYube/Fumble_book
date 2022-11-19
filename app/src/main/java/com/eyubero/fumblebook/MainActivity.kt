package com.eyubero.fumblebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eyubero.fumblebook.ui.screen.LoginScreen
import com.eyubero.fumblebook.ui.commons.Routes
import com.eyubero.fumblebook.ui.screen.NewUserScreen
import com.eyubero.fumblebook.ui.theme.FumbleBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FumbleBookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navigateTo()
                }
            }
        }
    }
}

@Composable
fun navigateTo() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Login.route, builder = {
        composable(Routes.Login.route, content = { LoginScreen(navController = navController) })
        composable(Routes.NewUSer.route, content = { NewUserScreen(navController = navController)})
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FumbleBookTheme() {
        navigateTo()
    }
}

