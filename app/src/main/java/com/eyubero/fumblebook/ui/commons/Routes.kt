package com.eyubero.fumblebook.ui.commons

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object NewUSer : Routes("new_user")
}