package com.example.naivybeats.adapters

import Tools
import android.graphics.BitmapFactory
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.function.ToLongFunction

class MessageAdapter(
    private val messageList: List<Message>,
    private val coroutineScope: CoroutineScope,
    private val user_actual_id: Int
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ShapeableImageView = view.findViewById(R.id.avatar)
        val userName: TextView = view.findViewById(R.id.user_name)
        val messageText: TextView = view.findViewById(R.id.message_data)
        val dateText: TextView = view.findViewById(R.id.date)
        val body : LinearLayout = view.findViewById(R.id.message_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_message_chat, parent, false)
        return MessageViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.messageText.text = message.text
        holder.dateText.text = message.publishDate
        coroutineScope.launch {
            val user = Tools.userOrRestaurant(message.userId)
            holder.userName.text = user?.name
            val file = user?.let { Tools.getImage(Tools.generatePathForImages(user.photo)) }
            file?.let {
                val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                holder.avatar.setImageBitmap(bitmap)
            }
            if (user?.user_id==user_actual_id){
                holder.body.background = holder.itemView.context.getDrawable(R.drawable.element_beig)
                holder.body.gravity = Gravity.END
            }
        }
    }
    override fun getItemCount(): Int = messageList.size
}