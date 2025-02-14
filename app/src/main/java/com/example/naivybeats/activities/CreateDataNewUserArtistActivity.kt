package com.example.naivybeats.activities

import Tools
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.naivybeats.R

class CreateDataNewUserArtistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_new_user_artist)
        val title =findViewById<TextView>(R.id.title)
        val editTextName = findViewById<EditText>(R.id.name)
        val editTextSurname = findViewById<EditText>(R.id.surname)
        val editTextEmail = findViewById<EditText>(R.id.email)
        val editTextNumber = findViewById<EditText>(R.id.number)
        val editTextPassword = findViewById<EditText>(R.id.password)
        val have_user = findViewById<TextView>(R.id.isUser)
        val button = findViewById<Button>(R.id.buttonContinue)
        stratInitialAnimations(title,editTextName,editTextSurname,editTextEmail,editTextNumber,editTextPassword,button,have_user)

        have_user.setOnClickListener(){
            Tools.createActivitySimple(this,LoginActivity::class.java)
        }

        button.setOnClickListener(){
            var list = mutableListOf<Int>()
            val name = editTextName.text
            if(name.isEmpty()){
                list.add(0)
            }
            val surname = editTextSurname.text
            if (surname.isEmpty()){
                list.add(1)
            }
            val email = editTextEmail.text
            if(email.isEmpty()){
                list.add(2)
            }
            val number = editTextNumber.text
            if(number.isEmpty()){
                list.add(3)
            }
            val password = editTextPassword.text
            if (password.isEmpty()){
                list.add(4)
            }
            if (!list.isEmpty()){
                shakeEditTexts(list)
            }else{
                Tools.createActivitySimple(this,GetDirectionActivity::class.java)
            }
        }
    }

    private fun shakeEditTexts(list: MutableList<Int>) {
        val listEditText = listOf<EditText>(findViewById(R.id.name),findViewById(R.id.surname),findViewById(R.id.email),findViewById(R.id.number),findViewById(R.id.password))
        list.forEach { index ->
            Tools.animationHorizontalShake(this,listEditText[index])
        }
    }

    private fun stratInitialAnimations(
        title: TextView,
        name: EditText,
        surname: EditText,
        email: EditText,
        number: EditText,
        password: EditText,
        button: Button,
        have_user: TextView
                                      ) {
        Tools.animationFocus(this,title)
        Tools.animationTurnUp(this,name)
        Tools.animationTurnUp(this,surname)
        Tools.animationTurnUp(this,email)
        Tools.animationTurnUp(this,number)
        Tools.animationTurnUp(this,password)
        Tools.animationTurnUp(this,button)
        Tools.animationTurnUp(this,have_user)
    }

}