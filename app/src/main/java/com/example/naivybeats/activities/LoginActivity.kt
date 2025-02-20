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
import com.example.naivybeats.R
import com.example.naivybeats.models.superUser.controller.SuperUserController
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        var users: List<Users>
        var editTextUser = findViewById<EditText>(R.id.userName)
        var editTextPassword = findViewById<EditText>(R.id.password)
        var button = findViewById<Button>(R.id.buttonContinue)
        var textViewNotUser = findViewById<TextView>(R.id.notUser)
        var imageLogo = findViewById<ImageView>(R.id.imageLogo)
        stratInitialAnimations(editTextUser,editTextPassword, imageLogo,textViewNotUser,button)

        textViewNotUser.setOnClickListener(){
            Tools.createActivitySimple(this,TypeOfUserActivity::class.java)
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

    private fun getAllUsers(): Boolean
    {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }

}