package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.MutableLiveData
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("Little Lemon", MODE_PRIVATE)
    }

    private val isLogin = MutableLiveData<Boolean>()
    private val firstName = MutableLiveData<String>()
    private val lastName = MutableLiveData<String>()
    private val email = MutableLiveData<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLogin.value = sharedPreferences.getBoolean("login", false)
        firstName.value = sharedPreferences.getString("firstName", "")
        lastName.value = sharedPreferences.getString("lastName", "")
        email.value = sharedPreferences.getString("email", "")

        setContent {
            LittleLemonTheme {
                OnBoarding(isLogin, sharedPreferences, firstName, lastName, email)
            }
        }
    }
}

