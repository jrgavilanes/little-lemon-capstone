package com.example.little_lemon_capstone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.little_lemon_capstone.R
import com.example.little_lemon_capstone.helpers.getUserData
import com.example.little_lemon_capstone.helpers.logoutUser

@Composable
fun ProfileScreen(modifier: Modifier, onNavigateToOnboarding: () -> Unit) {


    val context = LocalContext.current

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }


    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {

        val (pref_firstName, pref_lastName, pref_email) = getUserData(context)
        firstName = pref_firstName
        lastName = pref_lastName
        email = pref_email
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(height = 100.dp, width = 200.dp)
                .align(Alignment.CenterHorizontally)
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .background(Color.White)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Personal information", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email TextField
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                readOnly = true
            )


            Spacer(modifier = Modifier.height(64.dp))

            // Register Button
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.my_yellow)),
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                onClick = {
                    logoutUser(context)
                    onNavigateToOnboarding()
                }) {

                Text(text = "Log out", color = Color.DarkGray, fontWeight = FontWeight.Bold)

            }


        }
    }

}