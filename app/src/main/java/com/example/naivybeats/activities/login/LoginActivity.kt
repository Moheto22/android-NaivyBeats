package com.example.naivybeats.activities

import Tools
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.activities.login.TypeOfUserActivity
import com.example.naivybeats.models.superUser.controller.SuperUserController
import com.example.naivybeats.models.time.controller.TimeController
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.user.controller.UserController
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity() {
    companion object{
        var  userController = UserController()
        var timeController = TimeController()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        var users: List<Users>
        var times: List<Time>

        lifecycleScope.launch {
            try {
               times = getAllTimes()
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error al obtener datos: ${e.message}")
            }
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

    private suspend fun getAllTimes(): List<Time> {
        return withContext(Dispatchers.IO) {
            try {
                timeController.getAllTimes()
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error al obtener tiempos: ${e.message}")
                emptyList()
            }
        }
    }
}