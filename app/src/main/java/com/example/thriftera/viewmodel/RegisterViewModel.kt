package com.example.thriftera.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thriftera.data.User
import com.example.thriftera.util.*
import com.example.thriftera.util.Constants.USER_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: StateFlow<Resource<User>> = _register.asStateFlow()

    private val _validation = Channel<RegisterFieldsState>(Channel.BUFFERED)
    val validation: Flow<RegisterFieldsState> = _validation.receiveAsFlow()

    fun createAccountWithEmailAndPassword(user: User, password: String) {
        viewModelScope.launch {
            if (checkValidation(user, password)) {
                _register.emit(Resource.Loading())
                try {
                    val result = withContext(Dispatchers.IO) {
                        firebaseAuth.createUserWithEmailAndPassword(user.email, password).await()
                    }
                    result.user?.let { firebaseUser ->
                        saveUserInfo(firebaseUser.uid, user)
                    }
                } catch (e: Exception) {
                    _register.emit(Resource.Error(e.message.toString()))
                }
            } else {
                val registerFieldsState = RegisterFieldsState(
                    validateEmail(user.email), validatePassword(password)
                )
                _validation.send(registerFieldsState)
            }
        }
    }

    private suspend fun saveUserInfo(userUid: String, user: User) {
        try {
            withContext(Dispatchers.IO) {
                db.collection(USER_COLLECTION)
                    .document(userUid)
                    .set(user)
                    .await()
                _register.emit(Resource.Success(user))
            }
        } catch (e: Exception) {
            _register.emit(Resource.Error(e.message.toString()))
        }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        return emailValidation is RegisterValidation.Success &&
                passwordValidation is RegisterValidation.Success
    }
}
