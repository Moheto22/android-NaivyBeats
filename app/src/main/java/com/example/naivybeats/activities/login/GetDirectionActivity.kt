package com.example.naivybeats.activities.login
import Tools
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.naivybeats.R
import com.example.naivybeats.activities.LoginActivity
import com.example.naivybeats.models.musician.model.Musician

class GetDirectionActivity : AppCompatActivity(){
    object constantsProject {
        const val musician = "MUSICIAN"
        const val restaurant= "RESTAURANT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_direction_new_user)
        val intent = intent
        val musician = intent.getSerializableExtra(constantsProject.musician) as Musician

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

        button.setOnClickListener(){
            var list = mutableListOf<Int>()
            var province = province.text.toString()
            if (province.isEmpty()){

                list.add(0)
            }
            var municipality = municipality.text.toString()
            if (municipality.isEmpty()){
                list.add(1)
            }
            var direction = adress.text.toString()
            if (direction.isEmpty()){
                list.add(2)
            }
            if (!list.isEmpty()){
                shakeEditTexts(list)
            }else{
                Tools.createActivitySimple(this, ChoseStyleArtistActivity::class.java)
                Tools.createActivityGetStylesTime(this, musician)
            }
        }
    }

    private fun shakeEditTexts(list: MutableList<Int>) {
        val listEditText = listOf<EditText>(findViewById(R.id.province),findViewById(R.id.municipality),findViewById(R.id.adress))
        list.forEach { index ->
            Tools.animationHorizontalShake(this, listEditText[index])
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