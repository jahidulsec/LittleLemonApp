package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
//            LittleLemonTheme {
//                OnBoarding(isLogin, sharedPreferences, firstName, lastName, email)
//            }
            MyNavigation(isLogin, sharedPreferences)
        }
    }
}


@Composable
fun MyNavigation(
    isLogin: MutableLiveData<Boolean>,
    sharedPreferences: SharedPreferences,
) {
    val navController = rememberNavController()
    val startDestinations = if (isLogin.value == true) Home.route else OnBoarding.route
    NavHost(navController = navController, startDestination = startDestinations) {
        composable(Home.route) {
            Home()
        }
        composable(Profile.route) {
            Profile()
        }
        composable(OnBoarding.route) {
            OnBoarding(isLogin, sharedPreferences, navController )
        }
    }
}
