package com.example.little_lemon_capstone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ORDER FOR DELIVERY",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        val scrollState = rememberScrollState()

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .horizontalScroll(scrollState)
        ) {
            Button(
                onClick = {
                    orderMenuItems = databaseMenuItems?.sortedBy { it.title } ?: emptyList()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray, // Fondo gris clarito
                    contentColor = Color.DarkGray // Color del texto (gris oscuro)
                ),
                modifier = Modifier.padding(0.dp),
            ) {
                Text(
                    text = "Starters",
                    fontWeight = FontWeight.Bold, // Texto en negrita (bold)
                    fontSize = 16.sp,
                    color = Color.DarkGray // Asegura que el color del texto sea gris oscuro
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    orderMenuItems = databaseMenuItems?.sortedBy { it.title } ?: emptyList()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray, // Fondo gris clarito
                    contentColor = Color.DarkGray // Color del texto (gris oscuro)
                ),
                modifier = Modifier.padding(0.dp),
            ) {
                Text(
                    text = "Mains",
                    fontWeight = FontWeight.Bold, // Texto en negrita (bold)
                    fontSize = 16.sp,
                    color = Color.DarkGray // Asegura que el color del texto sea gris oscuro
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    orderMenuItems = databaseMenuItems?.sortedBy { it.title } ?: emptyList()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray, // Fondo gris clarito
                    contentColor = Color.DarkGray // Color del texto (gris oscuro)
                ),
                modifier = Modifier.padding(0.dp),
            ) {
                Text(
                    text = "Desserts",
                    fontWeight = FontWeight.Bold, // Texto en negrita (bold)
                    fontSize = 16.sp,
                    color = Color.DarkGray // Asegura que el color del texto sea gris oscuro
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    orderMenuItems = databaseMenuItems?.sortedBy { it.title } ?: emptyList()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray, // Fondo gris clarito
                    contentColor = Color.DarkGray // Color del texto (gris oscuro)
                ),
                modifier = Modifier.padding(0.dp),
            ) {
                Text(
                    text = "Drinks",
                    fontWeight = FontWeight.Bold, // Texto en negrita (bold)
                    fontSize = 16.sp,
                    color = Color.DarkGray // Asegura que el color del texto sea gris oscuro
                )
            }

        }

        HorizontalDivider(
            modifier = Modifier.padding(16.dp), // Opcional: Padding alrededor de la línea
            thickness = 1.dp,   // Grosor de la línea
            color = Color.Gray // Color de la línea
        )

        Spacer(modifier = Modifier.height(8.dp))

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

}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
//        modifier = Modifier
//            .fillMaxHeight()
//            .padding(top = 20.dp)
    ) {

        items(
            items = items,
            itemContent = { menuItem ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)){
                    Text(text = menuItem.title, fontWeight = FontWeight.Bold)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(2f)) {
                            Text(text = menuItem.description)
                            Text(text = "$${menuItem.price}" )
                        }
                        Spacer(modifier = Modifier.width(2.dp))
//                        Text(text = menuItem.image)
                        AsyncImage(
                            model = menuItem.image,
                            contentDescription = "Image from URL",
                            modifier = Modifier
                                .weight(1f)
//                                .size(128.dp) // Tamaño de la imagen
                                .clip(RoundedCornerShape(8.dp)), // Opcional: bordes redondeados
                            contentScale = ContentScale.Crop // Cómo ajustar la imagen dentro del contenedor
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp), // Opcional: Padding alrededor de la línea
                        thickness = 1.dp,   // Grosor de la línea
                        color = Color.LightGray // Color de la línea
                    )
                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    Text(menuItem.title)
//                    Text(
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(5.dp),
//                        textAlign = TextAlign.Right,
//                        text = "%.2f".format(menuItem.price)
//                    )
//                }
            }
        )
    }
}