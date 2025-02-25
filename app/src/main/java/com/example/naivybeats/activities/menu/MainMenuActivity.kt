package com.example.naivybeats.activities.menu

import Tools
import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.naivybeats.R

class MainMenuActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)
        supportFragmentManager.commit {
            replace<FragmentMenuArtist>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        var location = 0
        val listTextButtons = listOf<TextView>(findViewById(R.id.textHome), findViewById(R.id.textSearch), findViewById(R.id.textPublicate), findViewById(R.id.textChat), findViewById(R.id.textEdit))
        val home = findViewById<ImageView>(R.id.home)
        val search = findViewById<ImageView>(R.id.search)
        val publication = findViewById<ImageView>(R.id.publicate)
        val chat = findViewById<ImageView>(R.id.chat)
        val edit = findViewById<ImageView>(R.id.edit)

        home.setOnClickListener(){
            if (location!=0){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 0
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,home)
            }
        }
        search.setOnClickListener(){
            if(location!=1){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 1
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,search)
            }
        }
        publication.setOnClickListener(){
            if(location!=2){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 2
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,publication)
            }
        }
        chat.setOnClickListener(){
            if(location!=3){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 3
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,chat)
            }
        }
        edit.setOnClickListener(){
            if (location!=4){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 4
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,edit)
            }
        }

    }
}