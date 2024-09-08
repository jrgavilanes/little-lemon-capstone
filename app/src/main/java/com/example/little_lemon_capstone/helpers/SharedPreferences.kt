package com.example.little_lemon_capstone.helpers

import android.content.Context
import androidx.core.content.edit

fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit {
        putString("first_name", firstName)
        putString("last_name", lastName)
        putString("email", email)
        apply() // Guardar los datos de forma asincrónica
    }
}

fun checkUserRegistered(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("first_name", null)
    val lastName = sharedPreferences.getString("last_name", null)
    val email = sharedPreferences.getString("email", null)

    return firstName != null && lastName != null && email != null
}

fun logoutUser(context: Context) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit {
        clear()  // Esto borra todas las claves y valores en las preferencias
        apply()  // Guardar los cambios de manera asincrónica
    }
}