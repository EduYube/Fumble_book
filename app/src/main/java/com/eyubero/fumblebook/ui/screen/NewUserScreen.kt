package com.eyubero.fumblebook.ui.screen

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eyubero.fumblebook.model.User
import com.eyubero.fumblebook.ui.commons.CustomTopAppBar
import com.eyubero.fumblebook.ui.commons.Routes
import com.eyubero.fumblebook.ui.theme.FumbleBookTheme
import com.eyubero.fumblebook.ui.viewmodel.LoginViewModel

@Composable
fun NewUserScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val email = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val alias = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val buttonEnabled = remember { mutableStateOf(false) }
    val user = viewModel._user.observeAsState()

    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "New User",
                showBackIcon = true
            )
        },
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    })
            }, scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Fumble Book"
            )
            TextField(
                value = email.value,
                onValueChange = { email.value = it},
                label = { Text(text = "yourmail@gmail.com") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Email),
                        contentDescription = "default email icon"
                    )
                })
            TextField(
                value = firstName.value,
                onValueChange = { firstName.value = it},
                label = { Text(text = "first name") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Person),
                        contentDescription = "default person icon"
                    )
                })
            TextField(
                value = lastName.value,
                onValueChange = { lastName.value = it},
                label = { Text(text = "Last name") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Person),
                        contentDescription = "default person icon"
                    )
                })
            TextField(
                value = alias.value,
                onValueChange = { alias.value = it},
                label = { Text(text = "alias") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Favorite),
                        contentDescription = "default alias icon"
                    )
                })
            TextField(
                value = password.value,
                onValueChange = { password.value = it},
                label = { Text(text = "password") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Lock),
                        contentDescription = "default password icon"
                    )
                })
            TextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it
                                buttonEnabled.value = checkButtonState(email, firstName, lastName, alias, password, confirmPassword)},
                label = { Text(text = "confirm password") },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Lock),
                        contentDescription = "default password icon"
                    )
                })
            Button(enabled = buttonEnabled.value,
                onClick = {

                    if (password.value != confirmPassword.value) {
                        Toast.makeText(context, "Different password", Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.createUser(
                            User(
                                email = email.value,
                                first_name = firstName.value,
                                last_name = lastName.value,
                                alias = alias.value,
                                password = password.value
                            )
                        )
                    }
                    navController.navigate(Routes.Login.route)
                    Toast.makeText(context, user.toString(), Toast.LENGTH_LONG).show()
                }) {
                Text(text = "Create new user")
            }
        }
    }
}

fun checkButtonState(
    email: MutableState<String>,
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    alias: MutableState<String>,
    password: MutableState<String>,
    confirmPassword: MutableState<String>
): Boolean {
    return email.value.isNotBlank() &&
            firstName.value.isNotBlank() &&
            lastName.value.isNotBlank() &&
            alias.value.isNotBlank() &&
            password.value.isNotBlank() &&
            confirmPassword.value.isNotBlank()
}

@Preview(showBackground = true)
@Composable
fun PreviewNewUser() {
    FumbleBookTheme {
        NewUserScreen(navController = rememberNavController())
    }
}