package com.example.naivybeats.activities.menu

import Tools
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.post.model.PostDTO
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.style.model.Style
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.net.URI
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USER: String = "USER_ID"
private val PICK_IMAGE_REQUEST = 1


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPublicate.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPublicate : Fragment() {
    // TODO: Rename and change types of parameters
    private var user_id: Int? = null
    private var photoUpload: Boolean? = false
    private val buttonStates = HashMap<Button?, Boolean>()
    private var listOfButtons : List<Button?>? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            user_id = it.getInt(USER)
        }

        val create_publication = view.findViewById<LinearLayout>(R.id.create_publication)
        val create_offer = view.findViewById<LinearLayout>(R.id.create_offer)

        lifecycleScope.launch {
            val user = Tools.userOrRestaurant(user_id!!)
            when(user){
                is Musician-> setMusicianContent(create_publication,create_offer,user)
                is Restaurant -> setRestaurantContent(create_publication,create_offer,user)
            }

        }
    }

    private fun setRestaurantContent(
        createPublication: LinearLayout,
        createOffer: LinearLayout,
        user: Restaurant
    ) {
        createOffer.visibility = View.VISIBLE
        createPublication.visibility = View.GONE
        startMenuRestaurant(user)
    }

    private fun startMenuRestaurant(user: Restaurant) {
        val buttonDate = view?.findViewById<Button>(R.id.btnDate)
        val jazz = view?.findViewById<Button>(R.id.jazz)
        val hiphop = view?.findViewById<Button>(R.id.hiphop)
        val rock = view?.findViewById<Button>(R.id.rock)
        val pop = view?.findViewById<Button>(R.id.pop)
        val classic = view?.findViewById<Button>(R.id.classic)
        val trap = view?.findViewById<Button>(R.id.trap)
        val blues = view?.findViewById<Button>(R.id.blues)
        val reggueton = view?.findViewById<Button>(R.id.reggueton)
        val flamenco = view?.findViewById<Button>(R.id.flamenco)
        val tecno = view?.findViewById<Button>(R.id.tecno)
        val publicate = view?.findViewById<Button>(R.id.publicate_offer)
        listOfButtons = listOf(hiphop, pop, tecno, classic, flamenco, reggueton, rock, blues, jazz, trap)

        val onClickButton = View.OnClickListener { view ->
            when (view) {
                is Button -> {
                    if (!buttonStates[view]!!) {
                        view.setBackgroundResource(R.drawable.design_button_type_choose)
                        buttonStates[view] = true
                    }else {
                        view.setBackgroundResource(R.drawable.design_button_type_choose_not_pressed)
                        buttonStates[view] = false
                    }
                }
            }
        }
        listOfButtons!!.forEach { button ->
            buttonStates[button] = false
            button?.setOnClickListener(onClickButton)
        }

        buttonDate?.setOnClickListener {
            // Mostrar DatePicker primero
            val currentDate = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    // Cuando se selecciona la fecha, mostramos el TimePicker
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(Calendar.YEAR, year)
                    selectedDate.set(Calendar.MONTH, month)
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    TimePickerDialog(
                        requireContext(),
                        { _, hourOfDay, minute ->
                            selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            selectedDate.set(Calendar.MINUTE, minute)
                            // Formateamos la fecha y la ponemos en el botón
                            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                            buttonDate.text = format.format(selectedDate.time)

                        },
                        currentDate.get(Calendar.HOUR_OF_DAY),
                        currentDate.get(Calendar.MINUTE),
                        true
                    ).show()

                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        publicate?.setOnClickListener(){
            var error = false
            val listStylesSelected : List<Int>
            var salaryEditText = view?.findViewById<EditText>(R.id.salary)
            var dateButton = view?.findViewById<Button>(R.id.btnDate)
            var houresEditText = view?.findViewById<EditText>(R.id.houres)
            var descriptionEditText = view?.findViewById<EditText>(R.id.description_text)

            val salary = salaryEditText?.text.toString()
            if (salary.isEmpty()){
                error = true
            }
            val date = dateButton?.text.toString()
            if(date.isEmpty()){
                error = true
            }
            val description = descriptionEditText?.text.toString()
            if (description.isEmpty()){
                error = true
            }
            val houres = houresEditText?.text.toString()
            if (houres.isEmpty()){
                error =true
            }
            if(!buttonStates.any { it.value }){
                error = true
            }else{
                listStylesSelected = getPreferencesStyles()
            }
            if (error){
                Toast.makeText(requireContext(), getString(R.string.data_publication_eng), Toast.LENGTH_SHORT).show()
            }else{

            }
        }

    }
    private fun getPreferencesStyles(): List<Int> {
        val listResult: MutableList<Int> = mutableListOf()

        for (i in 0..<listOfButtons!!.size){
            if (buttonStates[listOfButtons?.get(i)] == true){
                listResult.add(i)
            }
        }
        return listResult
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMusicianContent(
        create_publication: LinearLayout,
        create_offer: LinearLayout,
        user: Musician
    ) {
        create_publication.visibility = View.VISIBLE
        create_offer.visibility = View.GONE
        startMenuMusician(user)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMenuMusician(user: Musician) {
        val panel_image = view?.findViewById<ImageView>(R.id.image_panel)
        val title = view?.findViewById<EditText>(R.id.title)
        val description = view?.findViewById<EditText>(R.id.description)
        val buttonAddPhoto = view?.findViewById<Button>(R.id.button_addPhoto)
        val buttonPublication = view?.findViewById<Button>(R.id.button_publication)

        buttonPublication?.setOnClickListener(){
            var error = false
            val title = title?.text.toString()

            if (title.isEmpty()){
                error = true
            }
            val description = description?.text.toString()
            if(description.isEmpty()){
                error = true
            }
            if (photoUpload != true){
                error = true
            }
            if (error) {
                Toast.makeText(requireContext(), getString(R.string.data_publication_eng), Toast.LENGTH_SHORT).show()
            } else {
                val bitmap = (panel_image?.drawable as BitmapDrawable).bitmap
                val file = bitmapToFile(requireContext(), bitmap, "img.png")

                lifecycleScope.launch {
                    Tools.insertPost(title, user_id!!, description, file)
                    Toast.makeText(requireContext(), "✔️ Publicación creada exitosamente", Toast.LENGTH_LONG).show()


                }
            }
        }
        buttonAddPhoto?.setOnClickListener(){
            openGallery()
        }
    }

    private fun bitmapToFile(context: Context, bitmap: Bitmap?, fileName: String): File {
        val file = File(context.cacheDir, fileName)
        file.createNewFile()

        val outputStream = FileOutputStream(file)
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        return file
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
        photoUpload = true

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            selectedImageUri?.let { uri ->
                // Mostrar la imagen en el ImageView
                val imageView = view?.findViewById<ImageView>(R.id.image_panel)
                imageView?.setImageURI(uri)
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                             ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publicate, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(user_id: Int?) = FragmentPublicate().apply {
            arguments = Bundle().apply {
                if (user_id != null) {
                    putInt(USER, user_id)
                }
            }
        }
    }
}