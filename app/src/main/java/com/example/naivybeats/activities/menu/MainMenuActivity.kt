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
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.user.model.Users
import java.io.Serializable


class MainMenuActivity: AppCompatActivity() {
    object constantsProject {
        const val USER ="USER"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)
        var direction = 0
        var user: Serializable? = intent.getSerializableExtra(constantsProject.USER)

        supportFragmentManager.commit {
            replace<FragmentMenu>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
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
                left_AnimationFragmentHome()
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
                    right_AnimationFragmentSearch()
                }else{
                    left_AnimationFragmentSearch()
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
                    right_AnimationFragmentPublicate()
                }else{
                    left_AnimationFragmentPublicate()
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
    private fun right_AnimationFragmentSearch() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentSearch>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentSearch() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentSearch>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentPublicate() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentPublicate>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun right_AnimationFragmentPublicate() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace<FragmentPublicate>(R.id.frameContainer)
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

    private fun left_AnimationFragmentHome() {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace<FragmentMenu>(R.id.frameContainer)
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
