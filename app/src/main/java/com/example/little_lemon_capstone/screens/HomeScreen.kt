package com.example.little_lemon_capstone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.little_lemon_capstone.AppDatabase
import com.example.little_lemon_capstone.MenuItemRoom
import com.example.little_lemon_capstone.R
import io.ktor.client.HttpClient
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigateToProfile: () -> Unit,
    database: AppDatabase,
    httpClient: HttpClient
) {

    val databaseMenuItems by database.menuItemDao().getAll().observeAsState()
    var orderMenuItems by remember { mutableStateOf<List<MenuItemRoom>>(emptyList()) }
    var menuItems by remember { mutableStateOf<List<MenuItemRoom>>(emptyList()) }

    var searchPhrase by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "logo",
//            modifier = Modifier.padding(50.dp)
//        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.size(75.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(height = 60.dp, width = 120.dp)
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                modifier = Modifier
                    .size(height = 75.dp, width = 75.dp)
                    .clickable { onNavigateToProfile() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(16.dp)
        ) {
            Text(
                text = "Little Lemon",
                fontSize = 36.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
            )
            Text(
                text = "Chicago",
                fontSize = 24.sp,
                color = Color.White,
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Bottom),
                    fontSize = 14.sp,
                    color = Color.White,
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist."
                )
                Image(
                    painter = painterResource(id = R.drawable.restaurant),
                    contentDescription = "profile",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .weight(1f), contentScale = ContentScale.Crop

                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = searchPhrase,
                onValueChange = { newText ->
                    searchPhrase = newText
                },
//                label = { Text("Search") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search icon",
                        tint = Color.White
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White, // Borde cuando está enfocado
                    unfocusedBorderColor = Color.White, // Borde cuando no está enfocado
                    cursorColor = Color.White // Color del cursor
                ),
                textStyle = TextStyle(color = Color.White),
                shape = RoundedCornerShape(16.dp),
                placeholder = { Text("Search", color = Color.Gray) },
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        // add Button code here
        Button(
            onClick = {
                orderMenuItems = databaseMenuItems?.sortedBy { it.title } ?: emptyList()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Tap to order by name")
        }




        // add is not empty check here

        menuItems = orderMenuItems.ifEmpty {
            databaseMenuItems ?: emptyList()
        }

        if (searchPhrase.isEmpty()) {
            MenuItemsList(items = menuItems)
        } else {
            menuItems.filter {
                it.title.lowercase(Locale.getDefault()).contains(
                    searchPhrase.lowercase(Locale.getDefault())
                )
            }.let { MenuItemsList(items = it) }
        }
    }

//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        Text(text = "Home nuevax")
//
//        Button(modifier = Modifier
//            .fillMaxWidth()
//            .padding(32.dp)
//            .height(50.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.my_yellow)), // Color amarillo de fondo
//            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
//            onClick = {
//                onNavigateToProfile()
//            }) {
//            Text(text = "Next", color = Color.DarkGray, fontWeight = FontWeight.Bold)
//        }
//    }
}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(menuItem.title)
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Right,
                        text = "%.2f".format(menuItem.price)
                    )
                }
            }
        )
    }
}