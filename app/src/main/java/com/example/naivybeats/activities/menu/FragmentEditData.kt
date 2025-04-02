package com.example.naivybeats.activities.menu

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.naivybeats.R
import com.example.naivybeats.models.user.model.Users

private const val USER: String = "ID"
private val PICK_IMAGE_REQUEST = 1

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEditData.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEditData : Fragment() {
    private var user: Users? = null
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(USER) as Users
        }

        val button_addPhoto = view?.findViewById<Button>(R.id.addPhoto)

        val avatar = view?.findViewById<ImageView>(R.id.avatar)
        var bitmap = (avatar?.drawable as BitmapDrawable).bitmap
        var size = minOf(bitmap.width, bitmap.height)
        var croppedBitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size)
        var scaledBitmap = Bitmap.createScaledBitmap(croppedBitmap, 250, 250, true)
        val roundedBitmapDrawable: RoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, scaledBitmap)
        roundedBitmapDrawable.isCircular = true

        avatar.setImageDrawable(roundedBitmapDrawable)

        val border_avatar = view?.findViewById<ImageView>(R.id.border_avatar)


        val circularDrawable = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(R.color.beig) // Establecer el color deseado
            setSize(100, 100) // Establecer el tamaño del círculo
        }
        border_avatar?.background = circularDrawable

        button_addPhoto?.setOnClickListener() {
            openGallery()
        }
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
        fun newInstance(param1: Users) = FragmentChat().apply {
            arguments = Bundle().apply {
                putSerializable(USER, param1)
            }
        }
    }
}