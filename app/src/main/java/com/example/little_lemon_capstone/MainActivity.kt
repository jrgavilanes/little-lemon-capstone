package com.example.little_lemon_capstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon_capstone.screens.HomeScreen
import com.example.little_lemon_capstone.screens.OnBoardingScreen
import com.example.little_lemon_capstone.screens.ProfileScreen
import com.example.little_lemon_capstone.ui.theme.LittlelemoncapstoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittlelemoncapstoneTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) { innerPadding ->
                    LittleLemonApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LittleLemonApp(modifier: Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {

        composable("onboarding") {
            OnBoardingScreen(modifier = modifier,
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("onboarding") {
                            inclusive = true
                        } // Borra la pantalla de Onboarding del backstack
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(modifier = modifier,
                onNavigateToProfile = {
                    navController.navigate("profile")
                }
            )
        }

        composable("profile") {
            ProfileScreen(
                modifier = modifier,
                onNavigateToOnboarding = {
                    navController.navigate("onboarding") {
                        popUpTo(0) { inclusive = true }
                    } // Delete backstack
                })
        }
    }


}








