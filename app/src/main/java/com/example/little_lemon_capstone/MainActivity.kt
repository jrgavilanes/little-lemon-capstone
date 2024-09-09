package com.example.little_lemon_capstone

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.little_lemon_capstone.helpers.checkUserRegistered
import com.example.little_lemon_capstone.screens.HomeScreen
import com.example.little_lemon_capstone.screens.OnBoardingScreen
import com.example.little_lemon_capstone.screens.ProfileScreen
import com.example.little_lemon_capstone.ui.theme.LittlelemoncapstoneTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

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
                    LittleLemonApp(modifier = Modifier.padding(innerPadding), httpClient, database)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO)
        {
            if (database.menuItemDao().isEmpty()) {
                // Fetch the menu from the network
                try {
                    val menuNetwork = fetchMenu()
                    saveMenuToDatabase(menuNetwork.menu)
                } catch (e: Exception) {
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Failed to fetch menu: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private suspend fun fetchMenu(): MenuNetwork {

        val url =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val response: HttpResponse = httpClient.get(url)
        val menuItems: MenuNetwork = response.body()
        httpClient.close()

        return menuItems
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}

@Composable
fun LittleLemonApp(modifier: Modifier, httpClient: HttpClient, database: AppDatabase) {

    val context = LocalContext.current

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
                },
                database,
                httpClient
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

    if (checkUserRegistered(context)) {
        navController.navigate("home") {
            popUpTo(0) { inclusive = true }
        }
    }


}








