
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.naivybeats.R

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Tools {
    companion object {
        /**
        fun createActivity(context: Context, activityClass: Class<*>, index: Int) {
            val intent = Intent(context, activityClass)
            intent.putParcelableArrayListExtra(LoginActivity.constantsProject.playersList, ArrayList(playersList))
            intent.putExtra(LoginActivity.constantsProject.index, index)
            context.startActivity(intent)
        }**/

        fun createActivitySimple(context: Context, activityClass: Class<*>) {
            val intent = Intent(context, activityClass)
            context.startActivity(intent)
        }
        fun animationTransparent(context: Context,view: View){
            val animation_trans = AnimationUtils.loadAnimation(context, R.anim.animation_trans)
            view.startAnimation(animation_trans)
        }
        fun animationFocus(context: Context,view: View){
            val animation_focus = AnimationUtils.loadAnimation(context, R.anim.animation_focus)
            view.startAnimation(animation_focus)
        }
        fun animationTurnUp(context: Context,view: View){
            val animation_turn_up = AnimationUtils.loadAnimation(context, R.anim.animation_turn_up)
            view.startAnimation(animation_turn_up)
        }

    }
}