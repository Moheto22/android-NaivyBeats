
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.naivybeats.R
import com.example.naivybeats.activities.CreateDataNewUserArtistActivity
import com.example.naivybeats.activities.GetDirectionActivity

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
        context.startActivity(intent)
        }
        fun createActivityGetAdressFromArtsit(context: Context,userType:String,name:String,surname:String,number:Int,email: String,password : String) {
            val intent = Intent(context, GetDirectionActivity::class.java)
            intent.putExtra(GetDirectionActivity.constantsProject.name, name)
            intent.putExtra(GetDirectionActivity.constantsProject.surname,surname)
            intent.putExtra(GetDirectionActivity.constantsProject.email,email)
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