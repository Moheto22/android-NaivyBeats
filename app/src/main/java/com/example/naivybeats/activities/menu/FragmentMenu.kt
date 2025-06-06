package com.example.naivybeats.activities.menu

import Tools
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.activities.adapter.PostAdapter
import com.example.naivybeats.adapters.OfferInAdapter
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.restaurant.model.Restaurant
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USER: String = "USER_ID"


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentMenu : Fragment() {
    private var user_id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            user_id = it.getInt(USER)
        }
        lifecycleScope.launch {
            val user = Tools.userOrRestaurant(user_id!!)
            val avatar = view.findViewById<ImageView>(R.id.avatar)
            val file = user?.let { Tools.getImage(Tools.generatePathForImages(user!!.photo)) }
            file?.let {
                val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                avatar.setImageBitmap(bitmap)
            }
            when(user){
                is Musician -> setMusicianContent(user)
                is Restaurant -> setRestaurantContent(user)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setRestaurantContent(user: Restaurant) {
        val panelSendOffer = view?.findViewById<LinearLayout>(R.id.sendOffert)
        val salarys = view?.findViewById<EditText>(R.id.salary)
        val date = view?.findViewById<TextView>(R.id.date_data)
        val buttonSend = view?.findViewById<Button>(R.id.send)
        val buttonCancel = view?.findViewById<Button>(R.id.cancell)
        lifecycleScope.launch {
        val listaDePublicaciones = Tools.getALlPosts(user.user_id)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = PostAdapter(listaDePublicaciones,lifecycleScope,requireContext(),user_id,panelSendOffer,salarys,date,buttonSend)
        recyclerView?.adapter = adapter
        }
        buttonCancel?.setOnClickListener {
            panelSendOffer?.visibility = View.GONE
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMusicianContent(user: Musician) {
        lifecycleScope.launch {
            val listaDeOfertas = Tools.getOffersIn(user.user_id)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView?.layoutManager = LinearLayoutManager(context)
            val adapter = OfferInAdapter(listaDeOfertas,lifecycleScope,requireContext(),user.user_id)
            recyclerView?.adapter = adapter
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                             ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(user_id: Int?) = FragmentMenu().apply {
            arguments = Bundle().apply {
                if (user_id != null) {
                    putInt(USER, user_id)
                }
            }
        }
    }
}

