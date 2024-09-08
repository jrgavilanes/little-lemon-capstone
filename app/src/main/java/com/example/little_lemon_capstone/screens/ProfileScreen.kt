package com.example.little_lemon_capstone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.little_lemon_capstone.R

@Composable
fun ProfileScreen(modifier: Modifier, onNavigateToOnboarding: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "profile nue")

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.my_yellow)), // Color amarillo de fondo
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            onClick = {
                onNavigateToOnboarding()
            }) {
            Text(text = "Next", color = Color.DarkGray, fontWeight = FontWeight.Bold)
        }
    }
}