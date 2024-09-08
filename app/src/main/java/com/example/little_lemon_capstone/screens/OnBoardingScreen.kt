package com.example.little_lemon_capstone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.little_lemon_capstone.R

@Composable
fun OnBoardingScreen(modifier: Modifier, onNavigateToHome: () -> Unit) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    val scrollState = rememberScrollState()

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .height(102.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Let's get to know you",
                fontSize = 24.sp,
                color = Color.White,
            )
        }

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
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
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
            )


            Spacer(modifier = Modifier.height(64.dp))

            // Register Button
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.my_yellow)), // Color amarillo de fondo
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                onClick = {
                    onNavigateToHome()
                }) {
                Text(text = "Register", color = Color.DarkGray, fontWeight = FontWeight.Bold)
            }

        }

    }
}