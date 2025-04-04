package com.example.naivybeats.activities.menu

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.naivybeats.R
import com.example.naivybeats.models.user.model.Users
import kotlinx.coroutines.launch
import java.io.Serializable

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
        fun newInstance(user_id: Int?) = FragmentChat().apply {
            arguments = Bundle().apply {
                if (user_id != null) {
                    putInt(USER, user_id)
                }
            }
        }
    }
}