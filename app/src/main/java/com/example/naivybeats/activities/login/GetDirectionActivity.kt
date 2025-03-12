package com.example.naivybeats.activities.login
import Tools
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.activities.LoginActivity
import com.example.naivybeats.activities.adapter.ProvinceAdapter
import com.example.naivybeats.models.municipality.model.Municipality
import com.example.naivybeats.models.province.models.City
import com.example.naivybeats.models.restaurant.model.Restaurant
import java.io.Serializable
import com.example.naivybeats.models.musician.model.Musician
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GetDirectionActivity : AppCompatActivity(){

    object constantsProject {
        const val user = "USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_direction_new_user)

        var provinces: List<City> = emptyList()
        var municipalitis: List<Municipality> = emptyList()

        val intent = intent
        val user = intent.getSerializableExtra(constantsProject.user)

        runBlocking {
            provinces = Tools.getAllProvinces()
            municipalitis = Tools.getAllMunicipalitis()
        }

        val title = findViewById<TextView>(R.id.title)
        val province = findViewById<Spinner>(R.id.province)
        var selectedProvince = City()
        val municipality = findViewById<AutoCompleteTextView>(R.id.municipality)
        val adress = findViewById<EditText>(R.id.adress)
        val button = findViewById<Button>(R.id.buttonContinue)
        val isUser = findViewById<TextView>(R.id.isUser)

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.municipality)

        val adapterM = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, municipalitis)
        autoCompleteTextView.setAdapter(adapterM)
        autoCompleteTextView.threshold = 1

        stratInitialAnimations(title,province,municipality,adress,button,isUser)

        val adapter = ProvinceAdapter(this, android.R.layout.simple_spinner_item, provinces)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        province.adapter = adapter

        province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
                                       ) {
                if (position != AdapterView.INVALID_POSITION) {
                    selectedProvince = provinces[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        isUser.setOnClickListener(){
            Tools.createActivitySimple(this, LoginActivity::class.java)
        }

        button.setOnClickListener(){
            var list = mutableListOf<Int>()
            val selectedPosition = province.selectedItemPosition

            if (selectedPosition == AdapterView.INVALID_POSITION){
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
            }else {
                if (musicianOrRestaurant(user) == 1){
                    var musician = user as Musician
                    setMusician(musician, selectedProvince)
                    Tools.createActivityPutExtraMusician(this, ChoseStyleArtistActivity::class.java, musician)
                    Tools.createActivityGetStylesTime(this, musician)

                } else if (musicianOrRestaurant(user) == 0){
                    //Crear la o las activitis correspondiente para restaurante

                } else {
                    Toast.makeText(this, "❌ Error al verificar si el usuario es restaurante o musico", Toast.LENGTH_LONG).show()
                }
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
        province: Spinner,
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

    private fun setMusician(musician: Serializable?, province: City){

    }

    private fun getMunicipalitis(){

    }

    fun musicianOrRestaurant(user: Serializable?): Int {
       return when (user) {
            is Musician -> return 1
            is Restaurant -> return 0
           else -> return -1
       }
    }

}