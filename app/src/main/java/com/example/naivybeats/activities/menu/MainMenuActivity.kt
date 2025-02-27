package com.example.naivybeats.activities.menu

import Tools
import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
//import androidx.fragment.app.commit
//import androidx.fragment.app.replace
import com.example.naivybeats.R

class MainMenuActivity: AppCompatActivity() {
    object constantsProject {
        const val TYPE = "type"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)
        val type = intent.getStringExtra(constantsProject.TYPE)
        if (type == "artist") {
            supportFragmentManager.commit {
                replace<FragmentMenuArtist>(R.id.frameContainer)
                setReorderingAllowed(true)
                addToBackStack("replacement")
            }
        }else{
            supportFragmentManager.commit {
                replace<FragmentMenuSpace>(R.id.frameContainer)
                setReorderingAllowed(true)
                addToBackStack("replacement")
            }
        }
        var location = 0
        val listTextButtons = listOf<TextView>(findViewById(R.id.text_home), findViewById(R.id.text_search), findViewById(R.id.text_publicate), findViewById(R.id.text_chat), findViewById(R.id.text_edit))
        val home = findViewById<ImageView>(R.id.home)
        val search = findViewById<ImageView>(R.id.search)
        val publication = findViewById<ImageView>(R.id.publicate)
        val chat = findViewById<ImageView>(R.id.chat)
        val edit = findViewById<ImageView>(R.id.edit)
        startAnimations(home,search,publication,chat,edit,listTextButtons)



        home.setOnClickListener(){
            if (location!=0){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 0
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,home)
                if (type == "artist") {
                    supportFragmentManager.commit {
                        replace<FragmentMenuArtist>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                }else{
                    supportFragmentManager.commit {
                        replace<FragmentMenuSpace>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                }
            }
        }
        search.setOnClickListener(){
            if(location!=1){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 1
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,search)
                if (type == "artist") {
                    supportFragmentManager.commit {
                        replace<FragmentSearchArtist>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                }else{
                    supportFragmentManager.commit {
                        replace<FragmentSearchSpace>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                }
            }
        }
        publication.setOnClickListener(){
            if(location!=2){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 2
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,publication)
                if (type == "artist") {
                    supportFragmentManager.commit {
                        replace<FragmentPublicateContent>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                }else{
                    supportFragmentManager.commit {
                        replace<FragmentPublicateOfert>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                }
            }
        }
        chat.setOnClickListener(){
            if(location!=3){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 3
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,chat)
                supportFragmentManager.commit {
                    replace<FragmentChat>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            }
        }
        edit.setOnClickListener(){
            if (location!=4){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 4
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,edit)
                supportFragmentManager.commit {
                    replace<FragmentEditData>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            }
        }

    }

    private fun left_rightAnimationFragment(fragmentChat: Fragment) {

    }

    private fun startAnimations(
        home: ImageView,
        search: ImageView,
        publication: ImageView,
        chat: ImageView,
        edit: ImageView,
        listTextButtons: List<TextView>,
        ) {
        Tools.animationFocus(this,home)
        Tools.animationFocus(this,search)
        Tools.animationFocus(this,publication)
        Tools.animationFocus(this,chat)
        Tools.animationFocus(this,edit)
        listTextButtons.forEach { item ->
            Tools.animationTurnUp(this,item)
        }


    }
}