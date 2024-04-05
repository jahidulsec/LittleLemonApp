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
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("Little Lemon", MODE_PRIVATE)
    }

    private val isLogin = MutableLiveData<Boolean>()

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()
            .menu
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLogin.value = sharedPreferences.getBoolean("login", false)


        setContent {
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
            Home(navController)
        }
        composable(Profile.route) {
            Profile(sharedPreferences, navController, isLogin)
        }
        composable(OnBoarding.route) {
            OnBoarding(isLogin, sharedPreferences, navController )
        }
    }
}
