package com.example.naivybeats.activities.menu


import Tools
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import kotlinx.coroutines.launch


class MainMenuActivity: AppCompatActivity() {
    object constantsProject {
        const val USER_ID ="USER_ID"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)
        var direction = 0
        var user_id: Int = intent.getIntExtra(constantsProject.USER_ID, -1)

        supportFragmentManager.commit {
            replace(R.id.frameContainer,FragmentMenu.newInstance(user_id))
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
                left_AnimationFragmentHome(user_id)
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
                    right_AnimationFragmentSearch(user_id)
                }else{
                    left_AnimationFragmentSearch(user_id)
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
                    right_AnimationFragmentPublicate(user_id)
                }else{
                    left_AnimationFragmentPublicate(user_id)
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
                    right_AnimationFragmentChat(user_id)
                }else{
                    left_AnimationFragmentChat(user_id)
                }
            }
        }
        edit.setOnClickListener(){
            if (location!=4){
                listTextButtons[location].setTypeface(null, Typeface.NORMAL)
                location = 4
                listTextButtons[location].setTypeface(null, Typeface.BOLD)
                Tools.animationPop(this,edit)
                right_AnimationFragmentEditData(user_id)
            }
        }
    }
    private fun right_AnimationFragmentSearch(user_id: Int) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace(R.id.frameContainer,FragmentSearch.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentSearch(user_id: Int) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace(R.id.frameContainer,FragmentSearch.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentPublicate(user_id: Int?) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace(R.id.frameContainer,FragmentPublicate.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun right_AnimationFragmentPublicate(user_id: Int?) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace(R.id.frameContainer,FragmentPublicate.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }


    private fun right_AnimationFragmentChat(user_id: Int) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace(R.id.frameContainer,FragmentChat.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentChat(user_id: Int) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace(R.id.frameContainer,FragmentChat.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun left_AnimationFragmentHome(user_id: Int) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_left,
                R.anim.animation_in_right
                               )
            replace(R.id.frameContainer,FragmentMenu.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private fun right_AnimationFragmentEditData(user_id: Int) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.animation_out_right,
                R.anim.animation_in_left
                               )
            replace(R.id.frameContainer, FragmentEditData.newInstance(user_id))
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
