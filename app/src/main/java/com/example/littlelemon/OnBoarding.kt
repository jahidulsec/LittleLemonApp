package com.example.littlelemon

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController

@SuppressLint("CommitPrefEdits")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoarding(
    isLogin: MutableLiveData<Boolean>,
    sharedPreferences: SharedPreferences,
    navController: NavHostController
) {
    
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    
    Column (
        modifier = Modifier
            .fillMaxSize()

    ) {
        val context = LocalContext.current
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .height(40.dp)
        )
        Text(
            text = "Let's get to know you",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = LittleLemonColor.gray,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LittleLemonColor.primary)
                .padding(horizontal = 20.dp, vertical = 40.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .padding(20.dp)
        ) {
            Text(
                text = "Personal Information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 40.dp)
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.yellow,
                    unfocusedBorderColor = LittleLemonColor.primary,
                    focusedLabelColor = LittleLemonColor.yellow,
                    unfocusedLabelColor = LittleLemonColor.primary
                ),
                textStyle = TextStyle(color = LittleLemonColor.primary),
                value = firstName,
                onValueChange = {firstName = it},
                label = { Text(text = "First Name")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.yellow,
                    unfocusedBorderColor = LittleLemonColor.primary,
                    focusedLabelColor = LittleLemonColor.yellow,
                    unfocusedLabelColor = LittleLemonColor.primary
                ),
                textStyle = TextStyle(color = LittleLemonColor.primary),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Last Name")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.yellow,
                    unfocusedBorderColor = LittleLemonColor.primary,
                    focusedLabelColor = LittleLemonColor.yellow,
                    unfocusedLabelColor = LittleLemonColor.primary
                ),
                textStyle = TextStyle(color = LittleLemonColor.primary),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.dp, color = LittleLemonColor.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onClick = {
                if(isLogin.value == false) {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_SHORT).show()
                    } else {
                        sharedPreferences.edit(commit = true) {
                            putBoolean("login", true)
                            putString("firstName", firstName)
                            putString("lastName", lastName)
                            putString("email", email)
                        }
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                        navController.navigate(Home.route)
                    }

                } else {
                    Toast.makeText(context, "Already logged in!", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Register", color = LittleLemonColor.dark)
        }
    }
}