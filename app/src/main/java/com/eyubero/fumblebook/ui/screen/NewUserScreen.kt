package com.eyubero.fumblebook.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eyubero.fumblebook.model.User
import com.eyubero.fumblebook.ui.theme.FumbleBookTheme
import com.eyubero.fumblebook.ui.viewmodel.LoginViewModel

@Composable
fun NewUserScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val email = remember {
        mutableStateOf("")
    }
    val first_name = remember {
        mutableStateOf("")
    }
    val last_name = remember {
        mutableStateOf("")
    }
    val alias = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember { mutableSetOf(false) }
    val confirmPasswordVisibility = remember { mutableSetOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "New user registration") }
            )
        },
        modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState
    ) {
        Column {
            Text(
                text = "Welcome to Fumble Book"
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "yourmail@gmail.com") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Email),
                        contentDescription = "default email icon"
                    )
                })
            OutlinedTextField(
                value = first_name.value,
                onValueChange = { first_name.value = it },
                label = { Text(text = "first name") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Person),
                        contentDescription = "default person icon"
                    )
                })
            OutlinedTextField(
                value = last_name.value,
                onValueChange = { last_name.value = it },
                label = { Text(text = "Last name") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Person),
                        contentDescription = "default person icon"
                    )
                })
            OutlinedTextField(
                value = alias.value,
                onValueChange = { alias.value = it },
                label = { Text(text = "alias") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Favorite),
                        contentDescription = "default alias icon"
                    )
                })
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(text = "password") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Lock),
                        contentDescription = "default password icon"
                    )
                })
            Button(onClick = {
                viewModel.createUser(
                    User(
                        email = email.value,
                        first_name = first_name.value,
                        last_name = last_name.value,
                        alias = alias.value,
                        password = password.value
                    )
                )
            }) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewUser(){
    FumbleBookTheme {
        NewUserScreen(navController = rememberNavController())
    }
}