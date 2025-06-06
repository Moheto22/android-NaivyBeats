package com.example.naivybeats.activities.login

import Tools
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.activities.LoginActivity
import com.example.naivybeats.models.musician.controller.MusicianController
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.launch
import java.security.MessageDigest

class CreateDataNewUserArtistActivity : AppCompatActivity() {
    companion object {
       var musicianController = MusicianController()
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_new_user_artist)
        val title =findViewById<TextView>(R.id.title)
        val editTextName = findViewById<EditText>(R.id.name)
        val editTextEmail = findViewById<EditText>(R.id.email)
        val editTextNumber = findViewById<EditText>(R.id.number)
        val editTextPassword = findViewById<EditText>(R.id.password)
        val have_user = findViewById<TextView>(R.id.isUser)
        val button = findViewById<Button>(R.id.buttonContinue)
        stratInitialAnimations(title,editTextName,editTextEmail,editTextNumber,editTextPassword,button,have_user)

        have_user.setOnClickListener(){
            Tools.createActivitySimple(this, LoginActivity::class.java)
        }

        button.setOnClickListener(){
            var list = mutableListOf<Int>()
            val name = editTextName.text
            if(name.isEmpty()){
                list.add(0)
            }
            val email = editTextEmail.text
            if(email.isEmpty()){
                list.add(2)
            }
            var number = editTextNumber.text.toString().toIntOrNull()
            if(number == null){
                list.add(3)
                number = -1
            }
            val password = editTextPassword.text
            if (password.isEmpty()){
                list.add(4)
            }
            if (!list.isEmpty()){
                shakeEditTexts(list)
            } else {

                var musician = newMusician(editTextName, editTextEmail, editTextPassword, editTextNumber)
               // Toast.makeText(this, "✔ Usuario creado exitosamente", Toast.LENGTH_LONG).show()
                Tools.createActivityGetAdressFromArtist(this, musician)
            }
        }
    }

    private fun shakeEditTexts(list: MutableList<Int>) {
        val listEditText = listOf<EditText>(findViewById(R.id.name),findViewById(R.id.email),findViewById(R.id.number),findViewById(R.id.password))
        list.forEach { index ->
            Tools.animationHorizontalShake(this,listEditText[index])
        }
    }

    private fun stratInitialAnimations(
        title: TextView,
        name: EditText,
        email: EditText,
        number: EditText,
        password: EditText,
        button: Button,
        have_user: TextView
                                      ) {
        Tools.animationFocus(this,title)
        Tools.animationTurnUp(this,name)
        Tools.animationTurnUp(this,email)
        Tools.animationTurnUp(this,number)
        Tools.animationTurnUp(this,password)
        Tools.animationTurnUp(this,button)
        Tools.animationTurnUp(this,have_user)
    }

    fun newMusician(editTextName: EditText, editTextEmail: EditText, editTextPassword: EditText, editTextNumber: EditText): Musician {
        val musician = Musician()
        musician.name = editTextName.text.toString()
        musician.email = editTextEmail.text.toString()
        musician.password = getHashPassword(editTextPassword)
        musician.phone_number = editTextNumber.text.toString()

        return musician
    }

    fun getHashPassword(editTextPassword: EditText): String {
        val password = editTextPassword.text.toString()
        return sha256(password)
    }

    fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}