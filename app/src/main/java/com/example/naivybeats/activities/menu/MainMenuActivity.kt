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
    object constantsProject {
        const val TYPE = "type"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)
        var direction = 0
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
                    left_AnimationFragmentHomeArtist()
                }else{
                    left_AnimationFragmentHomeSpace()
                }
            }
        }
        search.setOnClickListener(){
            if(location!=1){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                direction = 1 - location
                location = 1
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,search)
                if (direction > 0){
                    if (type == "artist") {
                        right_AnimationFragmentSearchArtist()
                    }else{
                        right_AnimationFragmentSearchSpace()
                    }
                }else{
                    if (type == "artist") {
                        left_AnimationFragmentSearchArtist()
                    }else{
                        left_AnimationFragmentSearchSpace()
                    }
                }
            }
        }
        publication.setOnClickListener(){
            if(location!=2){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                direction = 2 - location
                location = 2
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,publication)
                if (direction > 0){
                    if (type == "artist") {
                        right_AnimationFragmentPublicateContent()
                    }else{
                        right_AnimationFragmentPublicateOfert()
                    }
                }else{
                    if (type == "artist") {
                        left_AnimationFragmentPublicateContent()
                    }else{
                        left_AnimationFragmentPublicateOfert()
                    }
                }
            }
        }
        chat.setOnClickListener(){
            if(location!=3){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                direction = 3 - location
                location = 3
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,chat)
                if (direction > 0){
                    right_AnimationFragmentChat()
                }else{
                    left_AnimationFragmentChat()
                }
            }
        }
        edit.setOnClickListener(){
            if (location!=4){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 4
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,edit)
                right_AnimationFragmentEditData()
            }
        }
    }
    private fun right_AnimationFragmentSearchArtist() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentSearchArtist>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentSearchArtist() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentSearchArtist>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun right_AnimationFragmentSearchSpace() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentSearchSpace>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentSearchSpace() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentSearchSpace>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentPublicateContent() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentPublicateContent>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun right_AnimationFragmentPublicateContent() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentPublicateContent>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
    private fun right_AnimationFragmentPublicateOfert() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentPublicateOfert>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentPublicateOfert() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentPublicateOfert>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun right_AnimationFragmentChat() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentChat>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentChat() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentChat>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentHomeArtist() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentMenuArtist>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentHomeSpace() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentMenuSpace>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun right_AnimationFragmentEditData() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentEditData>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
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
