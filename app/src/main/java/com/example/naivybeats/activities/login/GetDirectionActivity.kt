package com.example.naivybeats.activities.login
import MunicipalityAdapter
import Tools
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.util.Calendar
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.activities.LoginActivity
import com.example.naivybeats.activities.adapter.ProvinceAdapter
import com.example.naivybeats.models.municipality.model.Municipality
import com.example.naivybeats.models.province.models.City
import com.example.naivybeats.models.restaurant.model.Restaurant
import java.io.Serializable
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.text.DecimalFormat
import java.util.Locale

class GetDirectionActivity : AppCompatActivity(){

    object constantsProject {
        const val user = "USER"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_direction_new_user)

        var provinces: List<City> = emptyList()
        var municipalities: List<Municipality> = emptyList()

        val intent = intent
        val user = intent.getSerializableExtra(constantsProject.user)

        runBlocking {
            provinces = Tools.getAllProvinces()
            municipalities = Tools.getAllMunicipalitis()
        }

        val title = findViewById<TextView>(R.id.title)
        val province = findViewById<Spinner>(R.id.province)
        var latitude:Double = 0.0
        var longitud:Double = 0.0
        var selectedProvince = City()
        val municipality = findViewById<AutoCompleteTextView>(R.id.municipality)
        val adress = findViewById<EditText>(R.id.adress)
        val button = findViewById<Button>(R.id.buttonContinue)
        val isUser = findViewById<TextView>(R.id.isUser)
        val buttonScheldule = findViewById<Button>(R.id.scheldule)
        var selectedMunicipality = Municipality()

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.municipality)
        val adapterM = MunicipalityAdapter(this, municipalities)
        autoCompleteTextView.setAdapter(adapterM)
        autoCompleteTextView.threshold = 1

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            selectedMunicipality = adapterM.getItem(position)!!
            val editableText = Editable.Factory.getInstance().newEditable(selectedMunicipality.name)
            autoCompleteTextView.text = editableText
        }


        if (musicianOrRestaurant(user) == 1){
            stratInitialAnimations(title,province,municipality,adress,button,isUser)
        }else{
            buttonScheldule.visibility = View.VISIBLE

            stratInitialAnimations(title,province,municipality,adress,button,isUser,buttonScheldule)

            buttonScheldule.setOnClickListener {
                // Paso 1: Elegir hora de inicio
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val timePickerStart = TimePickerDialog(this, { _, startHour, startMinute ->

                    // Paso 2: Elegir hora de fin (después de seleccionar inicio)
                    val timePickerEnd = TimePickerDialog(this, { _, endHour, endMinute ->

                        // Formateamos el texto y lo mostramos en el botón
                        val startTime = String.format("%02d:%02d", startHour, startMinute)
                        val endTime = String.format("%02d:%02d", endHour, endMinute)
                        buttonScheldule.text = "$startTime - $endTime"

                    }, hour, minute, true)

                    timePickerEnd.setTitle("Selecciona hora de fin")
                    timePickerEnd.show()

                }, hour, minute, true)

                timePickerStart.setTitle("Selecciona hora de inicio")
                timePickerStart.show()
            }
        }

        val adapter = ProvinceAdapter(this, android.R.layout.simple_spinner_item, provinces)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        province.adapter = adapter

        province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
                                       ) {
                if (position != AdapterView.INVALID_POSITION) {
                    selectedProvince = provinces[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        isUser.setOnClickListener(){
            Tools.createActivitySimple(this, LoginActivity::class.java)
        }

        button.setOnClickListener(){
            var list = mutableListOf<Int>()
            val selectedPosition = province.selectedItemPosition

            if (selectedPosition == AdapterView.INVALID_POSITION){
                list.add(0)
            }

            var municipality = municipality.text.toString()
            if (municipality.isEmpty()){
                list.add(1)
            }
            var direction = adress.text.toString()
            if (direction.isEmpty()) {
                list.add(2)
            }
            var scheldule = buttonScheldule.text.toString()
            if (scheldule.isEmpty() && musicianOrRestaurant(user)==2){
                list.add(3)
            }
            if (!list.isEmpty()){
                shakeEditTexts(list)
                Toast.makeText(this, getString(R.string.data_publication_eng), Toast.LENGTH_LONG).show()
            }else {
                if (musicianOrRestaurant(user) == 1){
                    val context = this
                    lifecycleScope.launch(Dispatchers.IO) {
                        var ubicacion = obtenerLatLngDesdeDireccion(context,municipality+", "+direction)
                        if (ubicacion == null){
                            ubicacion = obtenerLatLngDesdeDireccion(context,municipality)
                            if (ubicacion != null) {
                                latitude = ubicacion.first
                                longitud = ubicacion.second

                            }
                        }else{
                            latitude = ubicacion.first
                            longitud = ubicacion.second

                        }
                        var musician = user as Musician

                        setMusician(musician, selectedMunicipality,latitude.toString(),longitud.toString())
                        Tools.createActivityPutExtraMusician(context, ChoseStyleArtistActivity::class.java, musician)
                        Tools.createActivityGetStylesTime(context, musician)
                    }
                } else if (musicianOrRestaurant(user) == 0){
                    val context = this
                    lifecycleScope.launch {
                        var ubicacion = obtenerLatLngDesdeDireccion(context,municipality+", "+direction)
                        if (ubicacion == null){
                            ubicacion = obtenerLatLngDesdeDireccion(context,municipality)
                            if (ubicacion != null) {
                                latitude = ubicacion!!.first
                                longitud = ubicacion!!.second
                            }
                        }else{
                            latitude = ubicacion!!.first
                            longitud = ubicacion!!.second


                        }
                        var restaurant = user as Restaurant
                        val partes = scheldule.split(" - ")
                        setRestaurant(restaurant, selectedMunicipality, partes[0],partes[1],latitude.toString(),longitud.toString())
                        try {
                            var succes = Tools.newRestaurant(restaurant, drawableToFile(context,R.drawable.perfil,"img.png"))
                            Thread.sleep(1000)
                            var user_id = Tools.getUserIdByName(restaurant.name)
                            if (succes) {
                                Toast.makeText(this@GetDirectionActivity, "✔️ Restaurante creado exitosamente", Toast.LENGTH_LONG).show()
                                Tools.createActivityMenuMain(this@GetDirectionActivity, user_id)
                            } else {
                                Toast.makeText(this@GetDirectionActivity, "❌ Error al crear el restaurante", Toast.LENGTH_LONG).show()
                            }
                        } catch (e: Exception) {
                        Toast.makeText(this@GetDirectionActivity, "❌ Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "❌ Error al verificar si el usuario es restaurante o musico", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun shakeEditTexts(list: MutableList<Int>) {
        val listEditText = listOf<View>(findViewById(R.id.province),findViewById(R.id.municipality),findViewById(R.id.adress),findViewById(R.id.scheldule))
        list.forEach { index ->
            Tools.animationHorizontalShake(this, listEditText[index])
        }
    }
    fun obtenerLatLngDesdeDireccion(context: Context, direccion: String): Pair<Double, Double>? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val direcciones = geocoder.getFromLocationName(direccion, 1)

        return if (!direcciones.isNullOrEmpty()) {
            val location = direcciones[0]
            Pair(location.latitude, location.longitude)
        } else {
            null // Dirección no encontrada
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun stratInitialAnimations(
        title: TextView,
        province: Spinner,
        municipality: AutoCompleteTextView,
        adress: EditText,
        button: Button,
        isUser: TextView,
        buttonScheldule: Button
    ) {
        Tools.animationTurnUp(this,title)
        Tools.animationTurnUp(this,province)
        Tools.animationTurnUp(this,municipality)
        Tools.animationTurnUp(this,adress)
        Tools.animationTurnUp(this,button)
        Tools.animationTurnUp(this,buttonScheldule)
        Tools.animationTurnUp(this,isUser)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun stratInitialAnimations(
        title: TextView,
        province: Spinner,
        municipality: AutoCompleteTextView,
        adress: EditText,
        button: Button,
        isUser: TextView
    ) {
        Tools.animationTurnUp(this,title)
        Tools.animationTurnUp(this,province)
        Tools.animationTurnUp(this,municipality)
        Tools.animationTurnUp(this,adress)
        Tools.animationTurnUp(this,button)
        Tools.animationTurnUp(this,isUser)
    }

    private fun setMusician(
        musician: Musician,
        municipality: Municipality,
        latitude: String,
        longitud: String
    ){
        musician.province_id = municipality.municipalityId
        musician.latitud =latitude
        musician.longitud = longitud
    }

    fun musicianOrRestaurant(user: Serializable?): Int {
       return when (user) {
            is Musician -> return 1
            is Restaurant -> return 0
           else -> return -1
       }
    }

    fun getLatLongFromAddressOSM(address: String): Pair<Double?, Double?> {
        val encodedAddress = address.replace(" ", "+")
        val urlString = "https://nominatim.openstreetmap.org/search?q=$encodedAddress&format=json"

        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val response = inputStream.bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(response)

                if (jsonArray.length() > 0) {
                    val location = jsonArray.getJSONObject(0)
                    val latitude = location.getDouble("lat")
                    val longitude = location.getDouble("lon")

                    Pair(latitude, longitude)
                } else {
                    Pair(null, null)
                }
            } else {
                println("Error: ${connection.responseCode}")
                Pair(null, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Pair(null, null)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setRestaurant(
        restaurant: Restaurant,
        municipality: Municipality,
        start: String,
        end: String,
        latitude: String,
        longitud: String
    ) {
        restaurant.province_id = municipality.municipalityId
        //val (latitude, longitude) = getLatLongFromAddressOSM(direction)
        restaurant.latitud = latitude
        restaurant.longitud = longitud
        restaurant.openingTime = start
        restaurant.closingTime = end
        restaurant.creation_date = null
        restaurant.edition_date = null
        restaurant.photo = ""
        /*  if (latitude != null && longitude != null){

   } else {
       musician.latitud = null
       musician.longitud = null
   }*/
    }

    fun drawableToFile(context: Context, drawableId: Int, fileName: String): File {
        // 1. Obtener el drawable como bitmap
        val bitmap = BitmapFactory.decodeResource(context.resources, drawableId)

        // 2. Crear un archivo temporal
        val file = File(context.cacheDir, fileName) // o context.filesDir si lo quieres permanente
        file.createNewFile()

        // 3. Guardar el bitmap en el archivo como PNG (puedes cambiar a JPEG)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        return file
    }

}