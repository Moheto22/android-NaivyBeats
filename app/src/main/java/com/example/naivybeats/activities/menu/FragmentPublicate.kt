package com.example.naivybeats.activities.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.naivybeats.R
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.user.model.Users

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USER: String = "ID"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPublicate.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPublicate : Fragment() {
    // TODO: Rename and change types of parameters
    private var user: Users? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            user = it.getSerializable(USER) as Users
        }
        val create_publication = view.findViewById<LinearLayout>(R.id.create_publication)
        val create_offer = view.findViewById<LinearLayout>(R.id.create_offer)

        when(user){
            is Musician-> setMusicianContent(create_publication,create_offer)
            is Restaurant -> setRestaurantContent(create_publication,create_offer)

        }

    }

    private fun setRestaurantContent(createPublication: LinearLayout, createOffer: LinearLayout) {
        createOffer.visibility = View.VISIBLE
        createPublication.visibility = View.GONE
    }

    private fun setMusicianContent(create_publication: LinearLayout, create_offer: LinearLayout) {
        create_publication.visibility = View.VISIBLE
        create_offer.visibility = View.GONE
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                             ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publicate, container, false)
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