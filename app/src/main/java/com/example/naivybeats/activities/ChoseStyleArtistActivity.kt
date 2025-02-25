package com.example.naivybeats.activities

import Tools
import android.os.Bundle
import android.util.Range
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.naivybeats.R
import com.example.naivybeats.activities.GetDirectionActivity.constantsProject

class ChoseStyleArtistActivity : AppCompatActivity() {
    private val buttonStates = HashMap<Button, Boolean>()
    private var listOfButtons = listOf<Button>()
    object constantsProject {
        const val name = "NAME"
        const val surname = "SURNAME"
        const val password = "PASSWORD"
        const val email = "EMAIL"
        const val number = "NUMBER"
        const val municipality = "MUNICIPALITY"
        const val province = "PROVINCE"
        const val adress = "ADRESS"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chose_style_artist)
        val name = intent.getStringExtra(com.example.naivybeats.activities.ChoseStyleArtistActivity.constantsProject.name)
        val surname = intent.getStringExtra(com.example.naivybeats.activities.ChoseStyleArtistActivity.constantsProject.surname)
        val email = intent.getStringExtra(com.example.naivybeats.activities.ChoseStyleArtistActivity.constantsProject.email)
        val number  = intent.getIntExtra(com.example.naivybeats.activities.ChoseStyleArtistActivity.constantsProject.number, -1)
        val password = intent.getStringExtra(com.example.naivybeats.activities.ChoseStyleArtistActivity.constantsProject.password)

        val title = findViewById<TextView>(R.id.title)
        val subtitle_time = findViewById<TextView>(R.id.subtitle_time)
        val subtitle_style = findViewById<TextView>(R.id.subtitle_style)
        val morning = findViewById<Button>(R.id.morning)
        val afternoon = findViewById<Button>(R.id.afternoon)
        val night = findViewById<Button>(R.id.night)
        val whenever = findViewById<Button>(R.id.whenever)
        val jazz = findViewById<Button>(R.id.jazz)
        val hiphop = findViewById<Button>(R.id.hiphop)
        val rock = findViewById<Button>(R.id.rock)
        val pop = findViewById<Button>(R.id.pop)
        val classic = findViewById<Button>(R.id.classic)
        val trap = findViewById<Button>(R.id.trap)
        val blues = findViewById<Button>(R.id.blues)
        val reggueton = findViewById<Button>(R.id.reggueton)
        val flamenco = findViewById<Button>(R.id.flamenco)
        val tecno = findViewById<Button>(R.id.tecno)
        val button_continue = findViewById<Button>(R.id.buttonContinue)
        val is_user = findViewById<TextView>(R.id.isUser)
        listOfButtons = listOf<Button>(morning,afternoon,night,whenever,jazz,hiphop,rock,
                          pop,classic,trap,blues,reggueton,flamenco,tecno)


        startInitialAnimations(title,subtitle_time,subtitle_style,morning,afternoon,night,whenever,jazz,hiphop,rock,pop,classic,trap,blues,reggueton,flamenco,tecno,button_continue,is_user)
        val onClickButton = View.OnClickListener { view ->
            when (view) {
                is Button -> {
                    if (!buttonStates[view]!!) {
                        view.setBackgroundResource(R.drawable.design_button_type_choose)
                        buttonStates[view] = true
                        if (listOfButtons.indexOf(view) == 3){
                            offTimeButtons()
                        }else if(listOfButtons.indexOf(view) in 0..2){
                            listOfButtons[3].setBackgroundResource(R.drawable.design_button_type_choose_not_pressed)
                            buttonStates[listOfButtons[3]] = false
                        }
                        if(buttonStates[listOfButtons[0]] == true && buttonStates[listOfButtons[1]] == true && buttonStates[listOfButtons[2]] == true){
                            offTimeButtons()
                            listOfButtons[3].setBackgroundResource(R.drawable.design_button_type_choose)
                            buttonStates[listOfButtons[3]] = true
                        }
                    }else{
                        view.setBackgroundResource(R.drawable.design_button_type_choose_not_pressed)
                        buttonStates[view] = false
                    }
                }
            }
        }
        listOfButtons.forEach { button ->
            buttonStates[button] = false
            button.setOnClickListener(onClickButton)
        }
        button_continue.setOnClickListener(){
            val list_preferences_styles = getPreferencesStyles()
            val list_preferences_time = getPreferencesTime()

        }


    }

    private fun getPreferencesTime(): List<String> {
        val listResult = mutableListOf<String>()
        val listOfValues = listOf("morning","afternoon","night")
        if (buttonStates[listOfButtons[3]] == true){
            listResult.add("whenever")
        }else {
            for (i in 0..2){
                if (buttonStates[listOfButtons[i]] == true){
                    listResult.add(listOfValues[i])
                }
            }
        }
        return listResult
    }

    private fun getPreferencesStyles(): List<String> {
        val listResult = mutableListOf<String>()
        val listOfValues = listOf("Hip-hop","Pop","Tecno","Classic","Flamenco","Reggueton","Rock","Blues","Jazz","Trap")
        for (i in 4..listOfButtons.size){
            if (buttonStates[listOfButtons[i]] == true){
                listResult.add(listOfValues[i])
            }
        }
        return listResult
    }

    private fun offTimeButtons() {
        val range = 0..2
        range.forEach{ index ->
            listOfButtons[index].setBackgroundResource(R.drawable.design_button_type_choose_not_pressed)
            buttonStates[listOfButtons[index]] = false
        }
    }

    private fun startInitialAnimations(
        title: TextView,
        subtitle_time: TextView,
        subtitle_style: TextView,
        morning: Button,
        afternoon: Button,
        night: Button,
        whenever: Button,
        jazz: Button,
        hiphop: Button,
        rock: Button,
        pop: Button,
        classic: Button,
        trap: Button,
        blues: Button,
        reggueton: Button,
        flamenco: Button,
        tecno: Button,
        button_continue: Button,
        is_user: TextView
                                      ) {
        Tools.animationTurnUp(this,title)
        Tools.animationTurnUp(this,subtitle_time)
        Tools.animationTurnUp(this,subtitle_style)
        Tools.animationFocus(this,morning)
        Tools.animationFocus(this,afternoon)
        Tools.animationFocus(this,night)
        Tools.animationFocus(this,whenever)
        Tools.animationFocus(this,jazz)
        Tools.animationFocus(this,hiphop)
        Tools.animationFocus(this,rock)
        Tools.animationFocus(this,pop)
        Tools.animationFocus(this,classic)
        Tools.animationFocus(this,trap)
        Tools.animationFocus(this,blues)
        Tools.animationFocus(this,reggueton)
        Tools.animationFocus(this,flamenco)
        Tools.animationFocus(this,tecno)
        Tools.animationTurnUp(this,button_continue)
        Tools.animationTurnUp(this,is_user)

    }
}