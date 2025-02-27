
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.naivybeats.R
import com.example.naivybeats.activities.login.ChoseStyleArtistActivity
import com.example.naivybeats.activities.login.GetDirectionActivity
import com.example.naivybeats.activities.menu.MainMenuActivity

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

        fun createActivityGetAdressFromArtist(
            context: Context,
            name: String,
            surname: String,
            number: Int,
            email: String,
            password: String
                                             ) {
            val intent = Intent(context, GetDirectionActivity::class.java)
            intent.putExtra(GetDirectionActivity.constantsProject.type, "artist")
            intent.putExtra(GetDirectionActivity.constantsProject.name, name)
            intent.putExtra(GetDirectionActivity.constantsProject.surname, surname)
            intent.putExtra(GetDirectionActivity.constantsProject.email, email)
            intent.putExtra(GetDirectionActivity.constantsProject.number, number)
            intent.putExtra(GetDirectionActivity.constantsProject.password, password)
            context.startActivity(intent)
        }

        fun createActivityMenuMain(context: Context, type: String) {
            val intent = Intent(context, MainMenuActivity::class.java)
            intent.putExtra(MainMenuActivity.constantsProject.TYPE, type)
            context.startActivity(intent)
        }

        fun createActivityGetAdressFromSpace(
            context: Context,
            name: String,
            number: Int,
            email: String,
            password: String
                                            ) {
            val intent = Intent(context, GetDirectionActivity::class.java)
            intent.putExtra(GetDirectionActivity.constantsProject.type, "space")
            intent.putExtra(GetDirectionActivity.constantsProject.name, name)
            intent.putExtra(GetDirectionActivity.constantsProject.email, email)
            intent.putExtra(GetDirectionActivity.constantsProject.number, number)
            intent.putExtra(GetDirectionActivity.constantsProject.password, password)
            context.startActivity(intent)
        }

        fun createActivityGetStylesTime(
            context: Context,
            name: String?,
            surname: String?,
            password: String?,
            number: Int,
            email: String?,
            province: String,
            municipality: String,
            adress: String
                                       ) {
            val intent = Intent(context, ChoseStyleArtistActivity::class.java)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.name, name)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.surname, surname)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.email, email)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.number, number)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.password, password)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.province, province)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.municipality, municipality)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.adress, adress)
            context.startActivity(intent)
        }

        fun createActivitySimple(context: Context, activityClass: Class<*>) {
            val intent = Intent(context, activityClass)
            context.startActivity(intent)
        }

        fun animationTransparent(context: Context, view: View) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_trans)
            view.startAnimation(animation)
        }

        fun animationFocus(context: Context, view: View) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_focus)
            view.startAnimation(animation)
        }

        fun animationTurnUp(context: Context, view: View) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_turn_up)
            view.startAnimation(animation)
        }

        fun animationHorizontalShake(context: Context, view: View) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_horizontal_shake)
            view.startAnimation(animation)
        }

        fun showError(context: Context, messaje: String) {
            Toast.makeText(context, messaje, Toast.LENGTH_SHORT).show()
        }

        fun animationPop(context: Context, view: View) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_pop)
            view.startAnimation(animation)
        }

        fun left_right_AnimationFragment(context: Context, fragment: Fragment) {

        }

        fun right_left_AnimationFragment(context: Context, view: View) {

        }
    }
}