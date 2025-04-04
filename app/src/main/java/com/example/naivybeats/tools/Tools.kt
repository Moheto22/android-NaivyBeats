
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.naivybeats.R
import com.example.naivybeats.activities.login.ChoseStyleArtistActivity
import com.example.naivybeats.activities.login.GetDirectionActivity
import com.example.naivybeats.activities.menu.MainMenuActivity
import com.example.naivybeats.models.municipality.controller.MunicipalityController
import com.example.naivybeats.models.municipality.model.Municipality
import com.example.naivybeats.models.province.models.City
import com.example.naivybeats.models.musician.controller.MusicianController
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.province.controller.ProvinceController
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.time.controller.TimeController
import com.example.naivybeats.models.user.model.Users
import com.example.naivybeats.models.restaurant.controller.RestaurantController
import com.example.naivybeats.models.style.model.Style
import com.example.naivybeats.models.style.controller.StyleController

import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.user.controller.UserController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.Serializable

class Tools{
    companion object {
        val timeController = TimeController()
        val musicianController = MusicianController()
        val userController = UserController()
        val restaurantController = RestaurantController()
        val cityController = ProvinceController()
        var municipalityController = MunicipalityController()
        var styleController = StyleController()
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
            musician: Musician
                                             ) {
            val intent = Intent(context, GetDirectionActivity::class.java)
            intent.putExtra(GetDirectionActivity.constantsProject.user, musician)

            context.startActivity(intent)
        }

        fun createActivityMenuMain(context: Context, user_id: Int) {
            val intent = Intent(context, MainMenuActivity::class.java)
            intent.putExtra(MainMenuActivity.constantsProject.USER_ID, user_id)
            context.startActivity(intent)
        }

        fun createActivityGetAdressFromSpace(
            context: Context,
            restaurant: Restaurant
                                            ) {
            val intent = Intent(context, GetDirectionActivity::class.java)
            intent.putExtra(GetDirectionActivity.constantsProject.user, restaurant)

            context.startActivity(intent)
        }

        fun createActivityGetStylesTime(
            context: Context,
            musician: Musician
                                       ) {
            val intent = Intent(context, ChoseStyleArtistActivity::class.java)
            intent.putExtra(ChoseStyleArtistActivity.constantsProject.musicianC, musician)

            context.startActivity(intent)
        }

        fun createActivitySimple(context: Context, activityClass: Class<*>) {
            val intent = Intent(context, activityClass)
            context.startActivity(intent)
        }

        fun createActivityPutExtra(context: Context, activityClass: Class<*>, user_id: Int) {
            val intent = Intent(context, activityClass)

            intent.putExtra("USER_ID", user_id)
            context.startActivity(intent)
        }

        fun createActivityPutExtraMusician(context: Context, activityClass: Class<*>, musician: Musician) {
            val intent = Intent(context, activityClass)

            intent.putExtra("USER_ID", musician)
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


        //-----------------------------------------------------------------------------------

        //TIMES
        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun getAllTimes(): List<Time> {
            return withContext(Dispatchers.IO) {
                timeController.getAllTimes()
            }
        }


        //MUNICIPIOS
        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun getAllMunicipalitis(): List<Municipality>{
            return withContext(Dispatchers.IO){
                municipalityController.getAllMunicipality()
            }
        }

        //USERS
        suspend fun getAllUsers(): List<Users>{
            return withContext(Dispatchers.IO) {
                userController.getAllUsers()
            }
        }

        //MUSICIAN
        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun getMusicianById(user_id: Int): Musician{
            return withContext(Dispatchers.IO) {
                musicianController.getMusicianById(user_id)!!
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun insertMusician(musician: Musician): Boolean{
            return withContext(Dispatchers.IO){
                musicianController.insertMusician(musician)!!
            }
        }

        //RESTAURANT
        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun getRestaurantById(user_id: Int): Restaurant{
            return withContext(Dispatchers.IO) {
                restaurantController.getRestaurantById(user_id)!!
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun newRestaurant(restaurant: Restaurant): Boolean {
            return withContext(Dispatchers.IO) {
                restaurantController.newRestaurant(restaurant)
            }
        }

        //PROVINCES
        suspend fun getAllProvinces(): List<City> {
            return withContext(Dispatchers.IO){
                cityController.getAllCities()
            }
        }

        //STYLES
        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun getAllStyles(): List<Style>{
            return withContext(Dispatchers.IO){
                styleController.getAllStyles()
            }
        }

        //FUNCTIONS
        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun userOrRestaurant(user_id: Int): Users {
            val userLog: Users = getMusicianById(user_id)

            if (userLog == null) {
                return getRestaurantById(user_id)
            } else {
                return userLog
            }
        }
    }
}