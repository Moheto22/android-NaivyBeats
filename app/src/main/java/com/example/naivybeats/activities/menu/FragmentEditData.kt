package com.example.naivybeats.activities.menu

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.launch

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
        }
        generateCircularImages()

        val button_addPhoto = view.findViewById<Button>(R.id.addPhoto)
        button_addPhoto.setOnClickListener {
            openGallery()
        }
    }

    private fun setDataInFragment(user: Users) {
        val name = view?.findViewById<EditText>(R.id.name_data)
        val description = view?.findViewById<EditText>(R.id.description_data)
        name?.setText(user.name)
        description?.setText(user.description)
    }

    @SuppressLint("ResourceAsColor")
    private fun generateCircularImages() {

        val avatar = view?.findViewById<ImageView>(R.id.avatar)

        // Verificar que el ImageView no sea nulo y que tenga un drawable
        if (avatar != null && avatar.drawable is BitmapDrawable) {
            val bitmapDrawable = avatar.drawable as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap

            val size = minOf(bitmap.width, bitmap.height)
            val croppedBitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size)
            val scaledBitmap = Bitmap.createScaledBitmap(croppedBitmap, 250, 250, true)

            val roundedBitmapDrawable: RoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, scaledBitmap)
            roundedBitmapDrawable.isCircular = true

            avatar.setImageDrawable(roundedBitmapDrawable)
        } else {
            // Opcional: Manejar el caso en que avatar no tenga una imagen
            Log.e("FragmentEditData", "El ImageView es nulo o no tiene un BitmapDrawable")
        }

        val border_avatar = view?.findViewById<ImageView>(R.id.border_avatar)
        val circularDrawable = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(ContextCompat.getColor(requireContext(), R.color.beig))
            setSize(100, 100)
        }
        border_avatar?.background = circularDrawable
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
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