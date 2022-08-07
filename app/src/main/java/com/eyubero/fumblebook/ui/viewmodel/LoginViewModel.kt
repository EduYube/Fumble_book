package com.eyubero.fumblebook.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyubero.fumblebook.model.User
import com.eyubero.fumblebook.model.Users
import com.eyubero.fumblebook.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val _user = MutableLiveData<Users>()

    fun createUser(user: User): LiveData<Users> {
        viewModelScope.launch {
            val result = repository.createUser(
                user
            )
            _user.postValue(result)
        }
        return _user
    }
}