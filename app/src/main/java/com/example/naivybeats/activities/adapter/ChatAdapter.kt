package com.example.naivybeats.adapters

import Tools
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.user.model.Users
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ChatAdapter(
    private val chatList: List<Chat>,
    private val coroutineScope: CoroutineScope,
    private val user: Users,
    private val listaChats: LinearLayout,
    private val panelChat: LinearLayout,
    private val listMessages: RecyclerView,
    private val context: Context,
    private val panelMessage: LinearLayout,
    private val nameUser: TextView,
    private val avatarUserChat: ImageView,
    private var id_of_user_chat: Int
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ShapeableImageView = itemView.findViewById(R.id.avatar_user)
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val element_chat : LinearLayout = itemView.findViewById(R.id.area_chat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_chat, parent, false)
        return ChatViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        if (chat.restaurantId == user.user_id){
            id_of_user_chat = chat.musicianId
        }else{
            id_of_user_chat = chat.restaurantId
        }
        coroutineScope.launch {
            val user_chat = Tools.userOrRestaurant(id_of_user_chat)
            holder.userName.text = user_chat?.name
            val file = user_chat?.let { Tools.getImage(Tools.generatePathForImages(user_chat.photo)) }
            file?.let {
                val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                holder.avatar.setImageBitmap(bitmap)
            }
        }
        holder.element_chat.setOnClickListener(){
            listaChats.visibility = View.INVISIBLE
            panelChat.visibility = View.VISIBLE
            panelMessage.visibility = View.VISIBLE


            coroutineScope.launch {
                val user_chat = Tools.userOrRestaurant(id_of_user_chat)
                nameUser.text = user_chat?.name
                val file = user_chat?.let { Tools.getImage(Tools.generatePathForImages(user_chat.photo)) }
                file?.let {
                    val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                    avatarUserChat.setImageBitmap(bitmap)
                }
                val listMessagesdata = Tools.getMessagesByChatId(chat.chatId)
                listMessages.layoutManager = LinearLayoutManager(context)
                listMessages.adapter = MessageAdapter(listMessagesdata,coroutineScope,user.user_id)
            }
        }
    }

    override fun getItemCount(): Int = chatList.size
}
