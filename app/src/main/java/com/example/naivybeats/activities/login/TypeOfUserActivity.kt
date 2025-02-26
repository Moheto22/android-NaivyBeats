package com.example.naivybeats.activities.login

import Tools
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.naivybeats.R
import com.example.naivybeats.activities.LoginActivity

class TypeOfUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chose_user)
        val filter = findViewById<ImageView>(R.id.filter)
        val left_block = findViewById<ImageView>(R.id.left_block)
        val right_block = findViewById<ImageView>(R.id.right_block)
        val right_image = findViewById<ImageView>(R.id.right_image)
        val left_image = findViewById<ImageView>(R.id.left_image)
        val have_user = findViewById<TextView>(R.id.isUser)
        val title = findViewById<TextView>(R.id.title)
        val left_text = findViewById<TextView>(R.id.left_text)
        val right_text = findViewById<TextView>(R.id.right_text)

        stratInitialAnimations(filter,left_block,left_text,left_image,right_block,right_image,right_text,have_user,title)

        have_user.setOnClickListener(){
            Tools.createActivitySimple(this, LoginActivity::class.java)
        }
        right_block.setOnClickListener(){
            Tools.createActivitySimple(this, CreateDataNewUserSpaceActivity::class.java)
        }
        left_block.setOnClickListener(){
            Tools.createActivitySimple(this, CreateDataNewUserArtistActivity::class.java)
        }
    }

    private fun stratInitialAnimations(
        filter: ImageView,
        leftBlock: ImageView,
        leftText: TextView,
        leftImage: ImageView,
        rightBlock: ImageView,
        rightImage: ImageView,
        rightText: TextView,
        haveUser: TextView,
        title: TextView) {
        Tools.animationTransparent(this,filter)
        Tools.animationTurnUp(this,title)
        Tools.animationTurnUp(this,leftBlock)
        Tools.animationTurnUp(this,rightBlock)
        Tools.animationFocus(this,leftImage)
        Tools.animationFocus(this,rightImage)
        Tools.animationFocus(this,leftText)
        Tools.animationFocus(this,rightText)
        Tools.animationTurnUp(this,haveUser)

    }
}