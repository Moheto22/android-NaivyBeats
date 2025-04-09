package com.example.naivybeats.activities.menu

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.adapters.ChatAdapter
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USER: String = "USER_ID"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentChat.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentChat : Fragment() {
    // TODO: Rename and change types of parameters
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
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewChats)
        var panelVisibleChat = view.findViewById<LinearLayout>(R.id.pantalla_lista_chat)
        var panelChat = view.findViewById<LinearLayout>(R.id.chat)
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        var listMessagesView = view.findViewById<RecyclerView>(R.id.recyclerView)
        var panelMessage = view.findViewById<LinearLayout>(R.id.panelMessage)
        var panelAccept = view.findViewById<LinearLayout>(R.id.panelAccept)
        var text = view.findViewById<EditText>(R.id.text)
        var sendButton = view.findViewById<ImageView>(R.id.button_send)
        lifecycleScope.launch {
            val user = Tools.userOrRestaurant(user_id!!)
            val list = Tools.getChatByUserId(user_id!!)
            recyclerView?.adapter = ChatAdapter(list, lifecycleScope, user!!,panelVisibleChat,panelChat,listMessagesView,panelMessage,panelAccept,text,sendButton)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                             ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
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