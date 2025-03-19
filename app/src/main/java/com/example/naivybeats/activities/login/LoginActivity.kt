package com.example.naivybeats.activities

import Tools
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.activities.login.TypeOfUserActivity
import com.example.naivybeats.activities.menu.MainMenuActivity
import com.example.naivybeats.models.municipality.model.Municipality
import com.example.naivybeats.models.musician.controller.MusicianController
import com.example.naivybeats.models.province.models.City
import com.example.naivybeats.models.restaurant.controller.RestaurantController
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.style.model.Style
import com.example.naivybeats.models.time.controller.TimeController
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.user.controller.UserController
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.launch
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        var users: List<Users>
        var styles: List<Style>

        var editTextUser = findViewById<EditText>(R.id.userName)
        var editTextPassword = findViewById<EditText>(R.id.password)
        var button = findViewById<Button>(R.id.buttonContinue)
        var textViewNotUser = findViewById<TextView>(R.id.notUser)
        var imageLogo = findViewById<ImageView>(R.id.imageLogo)

        stratInitialAnimations(editTextUser,editTextPassword, imageLogo,textViewNotUser,button)

        textViewNotUser.setOnClickListener(){
            Tools.createActivitySimple(this, TypeOfUserActivity::class.java)
        }

        button.setOnClickListener(){

            lifecycleScope.launch {
                try {
                    users = Tools.getAllUsers()
                    checkIfUserExists(users, editTextUser, editTextPassword)
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Error al obtener datos: ${e.message}")
                }
            }
        }
    }

    private fun stratInitialAnimations(
        editTextUser: EditText,
        editTextPassword: EditText,
        imageLogo: ImageView,
        textViewNotUser: TextView,
        button: Button) {
        Tools.animationFocus(this,imageLogo)
        Tools.animationTurnUp(this,editTextUser)
        Tools.animationTurnUp(this,editTextPassword)
        Tools.animationTurnUp(this,button)
        Tools.animationTurnUp(this,textViewNotUser)
    }

    private fun checkIfUserExists(users: List<Users>, editTextUser: EditText, editTextPassword: EditText) {
        val username = editTextUser.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
           return Toast.makeText(this, "⚠️ Por favor, completa todos los campos", Toast.LENGTH_LONG).show()
        }

        var user = users.find { it.name == username }

        if (user != null) {
            if (user.password == hashPassword(password)) {
                lifecycleScope.launch {
                   user = userOrRestaurant(user!!)
                }
                Tools.createActivityPutExtra(this, MainMenuActivity::class.java, user!!)

            } else {
               return Toast.makeText(this, "❌ Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        } else {
            return Toast.makeText(this, "❌ Este usuario no existe", Toast.LENGTH_LONG).show()
        }
    }


    fun hashPassword(password: String): String {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val encodedHash = digest.digest(password.toByteArray(StandardCharsets.UTF_8))
            return bytesToHex(encodedHash)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Error al generar hash SHA-256", e)
        }
    }

    private fun bytesToHex(hash: ByteArray): String {
        val hexString = StringBuilder()
        for (b in hash) {
            val hex = Integer.toHexString(0xff and b.toInt())
            if (hex.length == 1) {
                hexString.append('0')
            }
            hexString.append(hex)
        }
        return hexString.toString()
    }

    private suspend fun userOrRestaurant(user: Users): Users? {
        val userLog = Tools.getMusicianById(user)

        return if (userLog == null) {
            Tools.getRestaurantById(user)
        } else {
            userLog
        }
    }

}