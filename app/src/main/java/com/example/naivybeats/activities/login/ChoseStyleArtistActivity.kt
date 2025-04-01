package com.example.naivybeats.activities.login

import Tools
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.style.model.Style
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class ChoseStyleArtistActivity : AppCompatActivity() {
    private val buttonStates = HashMap<Button, Boolean>()
    private var listOfButtons = listOf<Button>()

    object constantsProject {
        const val musicianC = "MUSICIAN"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chose_style_artist)
        val musicianC = intent.getSerializableExtra(constantsProject.musicianC)

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

        var styles: List<Style>
        var times: List<Time>
        var musician = musicianC as Musician


        runBlocking {
           styles = Tools.getAllStyles()
           times = Tools.getAllTimes()
        }

        listOfButtons = listOf<Button>(morning,afternoon,night,whenever,hiphop,pop,
                          tecno,classic,flamenco,reggueton,rock,blues,jazz,trap)

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
                    }else {
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
            val list_preferences_styles = getPreferencesStyles(styles)
            val list_preferences_time = getPreferencesTime(times)
            musician.styles = list_preferences_styles
            musician.times = list_preferences_time
            musician.photo = ""

            lifecycleScope.launch {
                try {
                    val succes = Tools.insertMusician(musician)
                    if (succes) {
                        Toast.makeText(this@ChoseStyleArtistActivity, "✔️ Músico creado exitosamente", Toast.LENGTH_LONG).show()
                        var user = musician as Users
                        Tools.createActivityMenuMain(this@ChoseStyleArtistActivity, user)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@ChoseStyleArtistActivity, "❌ Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getPreferencesTime(times: List<Time>): List<Time> {
        val listResult: MutableList<Time> = mutableListOf()

        if (buttonStates[listOfButtons[3]] == true){
            listResult.add(times[3])
        }else {
            for (i in 0..2){
                if (buttonStates[listOfButtons[i]] == true){
                    listResult.add(times[i])
                }
            }
        }
        return listResult
    }

    private fun getPreferencesStyles(styles: List<Style>): List<Style> {
        val listResult: MutableList<Style> = mutableListOf()

        for (i in 4..<listOfButtons.size){
            if (buttonStates[listOfButtons[i]] == true){
                listResult.add(styles[i-4])
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
        is_user: TextView ) {
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