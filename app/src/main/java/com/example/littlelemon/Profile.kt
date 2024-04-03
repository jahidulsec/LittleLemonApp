package com.example.littlelemon

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController


@SuppressLint("CommitPrefEdits")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    sharedPreferences: SharedPreferences,
    navController: NavHostController,
    isLogin: MutableLiveData<Boolean>
) {
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    firstName.value = sharedPreferences.getString("firstName", "")
    lastName.value = sharedPreferences.getString("lastName", "")
    email.value = sharedPreferences.getString("email", "")

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .height(40.dp)
                .clickable {
                    navController.navigate(Home.route)
                }
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .padding(20.dp)
        ) {
            Text(
                text = "Personal Information",
                color = LittleLemonColor.dark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 40.dp)
            )
            val fName = firstName.observeAsState("")
            val lName = lastName.observeAsState("")
            val emailData = email.observeAsState("")

            OutlinedTextField(
                colors = outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.yellow,
                    unfocusedBorderColor = LittleLemonColor.primary,
                    focusedLabelColor = LittleLemonColor.yellow,
                    unfocusedLabelColor = LittleLemonColor.primary
                ),
                textStyle = TextStyle(color = LittleLemonColor.primary),
                value = fName.value,
                onValueChange = {firstName.value = it},
                label = { Text(text = "First Name")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
            )
            OutlinedTextField(
                colors = outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.yellow,
                    unfocusedBorderColor = LittleLemonColor.primary,
                    focusedLabelColor = LittleLemonColor.yellow,
                    unfocusedLabelColor = LittleLemonColor.primary
                ),
                textStyle = TextStyle(color = LittleLemonColor.primary),
                value = lName.value,
                onValueChange = { lastName.value = it },
                label = { Text(text = "Last Name")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
            OutlinedTextField(
                colors = outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.yellow,
                    unfocusedBorderColor = LittleLemonColor.primary,
                    focusedLabelColor = LittleLemonColor.yellow,
                    unfocusedLabelColor = LittleLemonColor.primary
                ),
                textStyle = TextStyle(color = LittleLemonColor.primary),
                value = emailData.value,
                onValueChange = { email.value = it },
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
                sharedPreferences.edit(commit = true) {
                    putBoolean("login", false)
                    Toast.makeText(context, "ID is logged out!", Toast.LENGTH_SHORT).show()
                    isLogin.value = false
                    navController.navigate(OnBoarding.route)
                }
            }
        ) {
            Text(text = "Log Out", color = LittleLemonColor.dark)
        }

    }
}