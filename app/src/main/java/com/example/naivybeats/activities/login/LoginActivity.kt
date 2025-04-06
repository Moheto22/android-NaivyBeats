package com.example.naivybeats.activities

import Tools
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.activities.login.TypeOfUserActivity
import com.example.naivybeats.activities.menu.MainMenuActivity
import com.example.naivybeats.models.musician.controller.MusicianController
import com.example.naivybeats.models.restaurant.controller.RestaurantController
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.time.controller.TimeController
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.user.controller.UserController
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.launch
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.UUID


class LoginActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        var users: List<Users> = emptyList()
        lifecycleScope.launch {
            users = Tools.getAllUsers()
        }

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
            checkIfUserExists(users, editTextUser, editTextPassword)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkIfUserExists(users: List<Users>, editTextUser: EditText, editTextPassword: EditText) {
        val username = editTextUser.text.toString()
        val password = editTextPassword.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            //val user = Users(1,"wd", "wd","wd","wd",123456789,null,null,null,1,null,null)
            //Tools.createActivityPutExtra(this, MainMenuActivity::class.java, user)
           return Toast.makeText(this, "⚠️ Por favor, completa todos los campos", Toast.LENGTH_LONG).show()
        }

        var user: Users? = users.find { it.name == username }


        if (user != null) {
             val hashedPassword = getHashPassword(password)
            if (user.password == hashedPassword) {
                Tools.createActivityPutExtra(this, MainMenuActivity::class.java,  user!!.user_id)

            } else {
               return Toast.makeText(this, "❌ Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        } else {
            return Toast.makeText(this, "❌ Este usuario no existe", Toast.LENGTH_LONG).show()
        }
    }


    fun getHashPassword(password: String): String {
        return sha256(password)
    }

    fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}