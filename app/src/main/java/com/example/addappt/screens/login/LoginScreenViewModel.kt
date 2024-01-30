package com.example.addappt.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addappt.models.data.AppUsers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading : LiveData<Boolean> = _loading

    fun signIn(
        email : String,
        password : String,
        navigateToHome : () -> Unit
    ) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Log.d("LoginViewModel", "Sign in Successful : ${task.result.toString()}")
                        navigateToHome.invoke()
                    } else {
                        Log.d("LoginViewModel", "Sign in unsuccessful : ${task.result.toString()}")
                    }
                }
        } catch (e : Exception) {
            Log.d("LoginViewModel", "Exception in Sign in attempt : ${e.message}")
        }

    }

    fun signUp(
        email : String,
        password : String,
        navigateToHome : () -> Unit
    ){
        if(_loading.value == false) {
            _loading.value = true

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        Log.d("LoginViewModel", "Sign up Successful : ${task.result.toString()}")
                        navigateToHome.invoke()
                    } else {
                        Log.d("LoginViewModel", "Sign up Unsuccessful : ${task.result.toString()}")
                    }
                    _loading.value = false
                }

        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = AppUsers(
            userId = userId!!,
            displayName = displayName!!,
            age = 36,
            id = null
        ).toMap()

        FirebaseFirestore.getInstance().collection("users").add(user)
    }
}