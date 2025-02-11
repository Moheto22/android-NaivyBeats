package com.example.naivybeats.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.naivybeats.R

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        var editTextUser = findViewById<EditText>(R.id.userName)
        var editTextPassword = findViewById<EditText>(R.id.password)
        var button = findViewById<Button>(R.id.buttonContinue)
        var textViewNotUser = findViewById<TextView>(R.id.notUser)
        var imageLogo = findViewById<ImageView>(R.id.imageLogo)
        val animationStart = AnimationUtils.loadAnimation(this, R.anim.animation_turn_up)
        val animationFocus = AnimationUtils.loadAnimation(this, R.anim.animation_focus)
        textViewNotUser.startAnimation(animationStart)
        button.startAnimation(animationStart)
        editTextPassword.startAnimation(animationStart)
        editTextUser.startAnimation(animationStart)
        imageLogo.startAnimation(animationFocus)
    }
}