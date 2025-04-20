package com.example.naivybeats.activities.menu

import Tools
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.naivybeats.R
import com.example.naivybeats.adapters.ChatAdapter
import com.example.naivybeats.adapters.MessageAdapter
import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.musician.model.Musician
import kotlinx.coroutines.delay
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
    private var numberOfMessages: Int? = null
    private var chat_id_chating:Int = 0
    private lateinit var listMessagesView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            user_id = it.getInt(USER)
        }

        var panelVisibleChat = view.findViewById<LinearLayout>(R.id.pantalla_lista_chat)
        var panelChat = view.findViewById<LinearLayout>(R.id.chat_panel)
        listMessagesView = view.findViewById(R.id.recyclerViewMessages)
        var panelMessage = view.findViewById<LinearLayout>(R.id.panelMessage)
        var panelAccept = view.findViewById<LinearLayout>(R.id.panelAccept)
        var text = view.findViewById<EditText>(R.id.text)
        var searcher = view.findViewById<EditText>(R.id.searcher)
        var sendButton = view.findViewById<ImageView>(R.id.button_send)
        val avatarUserChat = view.findViewById<ImageView>(R.id.avat_user_chat)
        val nameUser = view.findViewById<TextView>(R.id.name_user_chat)

        lifecycleScope.launch {
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewChats)
            recyclerView?.layoutManager = LinearLayoutManager(requireContext())
            val user = Tools.userOrRestaurant(user_id!!)
            val list = Tools.getChatByUserId(user_id!!)
            val adapterData = ChatAdapter(list, lifecycleScope, user!!,panelVisibleChat,panelChat,listMessagesView,requireContext(),panelMessage,nameUser,avatarUserChat,0)
            recyclerView?.adapter = adapterData
        }
        panelChat.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (v.visibility == View.VISIBLE) {

                lifecycleScope.launch {
                    delay(2000)
                    val user_chat = Tools.getUserIdByName(nameUser.text.toString())
                    val user_chat_real = Tools.userOrRestaurant(user_chat)
                    var chat : Chat
                    if (user_chat_real is Musician){
                        chat = Tools.getChatByMusicianAndRestaurantId(Chat(null,null,user_id!!,user_chat_real.user_id))
                    }else{
                        chat = Tools.getChatByMusicianAndRestaurantId(Chat(null,null,user_chat_real!!.user_id,user_id!!))
                    }
                    val list = Tools.getMessagesByChatId(chat.chatId)
                    numberOfMessages = list.size
                    chat_id_chating = chat.chatId
                    val handler = Handler(Looper.getMainLooper())
                    val runnable = object : Runnable {
                        override fun run() {
                            lifecycleScope.launch {
                                val list = Tools.getMessagesByChatId(chat.chatId)
                                if (list.size != numberOfMessages){
                                    listMessagesView.layoutManager= LinearLayoutManager(requireContext())
                                    listMessagesView.adapter = MessageAdapter(list,lifecycleScope,user_id!!)
                                    numberOfMessages = list.size
                                }
                            }

                            handler.postDelayed(this, 10000) // 2000ms = 2 segundos
                        }
                    }
                    // Iniciar la ejecución del runnable
                    handler.post(runnable)
                }

            }
        }
        sendButton.setOnClickListener(){
            val message = text.text.toString()
            lifecycleScope.launch {
                Tools.newMessage(Message(null,null,chat_id_chating,user_id!!,null,message))
                delay(500)
                val list = Tools.getMessagesByChatId(chat_id_chating)
                listMessagesView.layoutManager= LinearLayoutManager(requireContext())
                listMessagesView.adapter = MessageAdapter(list,lifecycleScope,user_id!!)
                numberOfMessages = list.size
                text.setText("")
            }
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