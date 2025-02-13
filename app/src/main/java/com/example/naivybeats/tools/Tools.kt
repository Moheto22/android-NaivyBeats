
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.naivybeats.R
import com.example.naivybeats.activities.CreateDataNewUserMusicActivity

class Tools {
    companion object {
        /**
        fun createActivity(context: Context, activityClass: Class<*>, index: Int) {
            val intent = Intent(context, activityClass)
            intent.putParcelableArrayListExtra(LoginActivity.constantsProject.playersList, ArrayList(playersList))
            intent.putExtra(LoginActivity.constantsProject.index, index)
            context.startActivity(intent)
        }**/

        fun createActivityNewDataUser(context: Context, activityClass: Class<*>, userType: String) {
        val intent = Intent(context, activityClass)
        intent.putExtra(CreateDataNewUserMusicActivity.constantsProject.userType, userType)
        context.startActivity(intent)
        }

        fun createActivitySimple(context: Context, activityClass: Class<*>) {
            val intent = Intent(context, activityClass)
            context.startActivity(intent)
        }
        fun animationTransparent(context: Context,view: View){
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_trans)
            view.startAnimation(animation)
        }
        fun animationFocus(context: Context,view: View){
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_focus)
            view.startAnimation(animation)
        }
        fun animationTurnUp(context: Context,view: View){
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_turn_up)
            view.startAnimation(animation)
        }
        fun animationHorizontalShake(context: Context,view: View){
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_horizontal_shake)
            view.startAnimation(animation)
        }
        fun showError(context: Context,messaje: String){
            Toast.makeText(context, messaje, Toast.LENGTH_SHORT).show()
        }

    }
}