package com.example.naivybeats.activities
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.naivybeats.R

class GetDirectionActivity : AppCompatActivity(){
    object constantsProject {
        const val name = "NAME"
        const val surname = "SURNAME"
        const val password = "PASSWORD"
        const val email = "EMAIL"
        const val number = "NUMBER"
        const val type = "TYPE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_direction_new_user)
        val intent = intent
        val name = intent.getStringExtra(constantsProject.name)
        val type = intent.getStringExtra(constantsProject.type)
        if(type == "artist"){
            val surname = intent.getStringExtra(constantsProject.surname)
        }
        val email = intent.getStringExtra(constantsProject.email)
        val number  = intent.getIntExtra(constantsProject.number,-1)
        val password = intent.getStringExtra(constantsProject.password)
        val title = findViewById<TextView>(R.id.title)
        val province = findViewById<AutoCompleteTextView>(R.id.province)
        val municipality = findViewById<AutoCompleteTextView>(R.id.municipality)
        val adress = findViewById<EditText>(R.id.adress)
        val button = findViewById<Button>(R.id.buttonContinue)
        val isUser = findViewById<TextView>(R.id.isUser)
        stratInitialAnimations(title,province,municipality,adress,button,isUser)

        val opciones = listOf("Opción 1", "Opción 2", "Opción 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, opciones)
        province.setAdapter(adapter)
        municipality.setAdapter(adapter)

        isUser.setOnClickListener(){
            Tools.createActivitySimple(this, LoginActivity::class.java)
        }

    }
    private fun stratInitialAnimations(
        title: TextView,
        province: AutoCompleteTextView,
        municipality: AutoCompleteTextView,
        adress: EditText,
        button: Button,
        isUser: TextView
                                      ) {
        Tools.animationTurnUp(this,title)
        Tools.animationTurnUp(this,province)
        Tools.animationTurnUp(this,municipality)
        Tools.animationTurnUp(this,adress)
        Tools.animationTurnUp(this,button)
        Tools.animationTurnUp(this,isUser)
    }
}