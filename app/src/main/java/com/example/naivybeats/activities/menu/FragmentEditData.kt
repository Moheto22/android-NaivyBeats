package com.example.naivybeats.activities.menu

import Tools
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

private const val USER: String = "USER_ID"
private val PICK_IMAGE_REQUEST = 1

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEditData.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEditData : Fragment() {
    private var user_id: Int? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            user_id = it.getInt(USER)
        }
        var user: Users? = null

        lifecycleScope.launch {
            user = Tools.userOrRestaurant(user_id!!)
            user?.let { setDataInFragment(it) }
            val avatar = view.findViewById<ImageView>(R.id.avatar)
            val file = user?.let { Tools.getImage(Tools.generatePathForImages(user!!.photo)) }
            file?.let {
                val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                avatar.setImageBitmap(bitmap)
            }
            if (user is Musician) {
                val numFollowers = view.findViewById<TextView>(R.id.value_1)
                val numFollowersValue = Tools.getFollows(user_id!!)
                numFollowers.text = numFollowersValue.toString()
                val numLikes = view.findViewById<TextView>(R.id.value_2)
                val numLikesValue = Tools.getPostLikes(user_id!!)
                numLikes.text = numLikesValue.toString()
            }else{
                val titleTotalOffers = view.findViewById<TextView>(R.id.key_1)
                titleTotalOffers.text = getString(R.string.total_offers_eng)
                val numOffers = view.findViewById<TextView>(R.id.value_1)
                val numOffersValue = Tools.getOffers(user_id!!)
                numOffers.text = numOffersValue.toString()
                val titleNumDone = view.findViewById<TextView>(R.id.key_2)
                titleNumDone.text = getString(R.string.total_concerts_done_eng)
                val numDone = view.findViewById<TextView>(R.id.value_2)
                val numDoneValue = Tools.getEventsDone(user_id!!)
                numDone.text = numDoneValue.toString()
            }
        }
        val button_save = view.findViewById<Button>(R.id.save)

        val button_addPhoto = view.findViewById<Button>(R.id.addPhoto)
        button_addPhoto.setOnClickListener {
            openGallery()
        }
        button_save.setOnClickListener(){
            val image = view.findViewById<ImageView>(R.id.avatar)
            val bitmap = (image?.drawable as BitmapDrawable).bitmap
            val file = bitmapToFile(requireContext(), bitmap, "img.png")
            val name = view.findViewById<EditText>(R.id.name_data)
            val description = view.findViewById<EditText>(R.id.description_data)
            val nameString = name.text.toString()
            val descriptionString = description.text.toString()
            lifecycleScope.launch {
                var succes = Tools.updateImage(file,user!!.photo,nameString,descriptionString,user_id!! as Integer)

                if (succes) {
                    Toast.makeText(requireContext(), "✔️ Datos actualizados con éxito", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setDataInFragment(user: Users) {
        val name = view?.findViewById<EditText>(R.id.name_data)
        val description = view?.findViewById<EditText>(R.id.description_data)
        name?.setText(user.name)
        description?.setText(user.description)
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            selectedImageUri?.let { uri ->
                // Mostrar la imagen en el ImageView
                val imageView = view?.findViewById<ImageView>(R.id.avatar)
                imageView?.setImageURI(uri)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                             ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_data, container, false)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: Int) = FragmentEditData().apply {
            arguments = Bundle().apply {
                putSerializable(USER, param1)
            }
        }
    }
}