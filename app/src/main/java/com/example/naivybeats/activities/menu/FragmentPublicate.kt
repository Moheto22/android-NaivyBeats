package com.example.naivybeats.activities.menu

import Tools
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
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
import com.example.naivybeats.models.restaurant.model.Restaurant
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import java.io.File
import java.io.FileOutputStream
import java.net.URI

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
    }

    private fun setMusicianContent(
        create_publication: LinearLayout,
        create_offer: LinearLayout,
        user: Musician
    ) {
        create_publication.visibility = View.VISIBLE
        create_offer.visibility = View.GONE
        startMenuMusician(user)
    }

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
            } else{
                val bitmap = (panel_image?.drawable as BitmapDrawable).bitmap
                val file = bitmapToFile(requireContext(), bitmap, "img.png")
                
            }
        }
        buttonAddPhoto?.setOnClickListener(){
            openGallery()
        }
    }

    private fun bitmapToFile(requireContext: Context, bitmap: Bitmap?, fileName: String): File {

        val file = File(context?.cacheDir, fileName)
        file.createNewFile()

        // Escribir el bitmap al archivo
        val outputStream = FileOutputStream(file)
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }
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