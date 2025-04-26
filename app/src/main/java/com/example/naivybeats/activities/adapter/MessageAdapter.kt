package com.example.naivybeats.adapters

import Tools
import android.graphics.BitmapFactory
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.musician.model.Musician
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
        val bodyAccept : LinearLayout = view.findViewById(R.id.panelAccept)
        val accept : Button = view.findViewById(R.id.accept)
        val decline : Button = view.findViewById(R.id.decline)
        val dateData : TextView = view.findViewById(R.id.date_data)
        val salary : TextView = view.findViewById(R.id.salary)
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
            val actualUser = Tools.userOrRestaurant(user_actual_id)
            if (message.offer != null){
                holder.bodyAccept.visibility = View.VISIBLE
                holder.body.visibility = View.GONE
                val list = message.text.split("|")
                holder.dateData.text = list[1]
                holder.salary.text = list[0]
                if (message.accept == 1){
                    if (user!!.user_id == user_actual_id){
                        holder.decline.background = holder.itemView.context.getDrawable(R.drawable.design_textbox)
                    }else{
                        holder.decline.background = holder.itemView.context.getDrawable(R.drawable.beig_trans_style)
                    }
                    holder.accept.background = holder.itemView.context.getDrawable(R.drawable.grenn_button_style)

                }else if(message.accept == -1){
                    if (user!!.user_id == user_actual_id){
                        holder.accept.background = holder.itemView.context.getDrawable(R.drawable.design_textbox)
                    }else{
                        holder.accept.background = holder.itemView.context.getDrawable(R.drawable.beig_trans_style)
                    }
                    holder.decline.background = holder.itemView.context.getDrawable(R.drawable.red_button_style)

                }
                if (user!!.user_id == user_actual_id && message.accept == null){
                    holder.accept.background = holder.itemView.context.getDrawable(R.drawable.element_bluesky)
                    holder.decline.background = holder.itemView.context.getDrawable(R.drawable.element_bluesky)
                }
                holder.accept.setOnClickListener {
                    if (message.accept == null && actualUser is Musician){
                        holder.accept.background = holder.itemView.context.getDrawable(R.drawable.grenn_button_style)
                        message.accept = 1
                        coroutineScope.launch {
                            Tools.responseMessage(1,message.messageId,user_actual_id)
                        }
                    }
                }
                holder.decline.setOnClickListener {
                    if (message.accept == null && actualUser is Musician){
                        holder.decline.background = holder.itemView.context.getDrawable(R.drawable.red_button_style)
                        message.accept = -1
                        coroutineScope.launch {
                            Tools.responseMessage(-1,message.messageId,user_actual_id)
                        }
                    }
                }
            }else{
                holder.userName.text = user?.name
                val file = user?.let { Tools.getImage(Tools.generatePathForImages(user.photo)) }
                file?.let {
                    val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                    holder.avatar.setImageBitmap(bitmap)
                }

            }
            if (user?.user_id == user_actual_id) {
                if (message.offer !=null){
                    holder.bodyAccept.background = holder.itemView.context.getDrawable(R.drawable.element_beig)
                    val params = holder.bodyAccept.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.END
                    params.marginStart = 385 // Margen izquierdo para mensajes enviados
                    params.marginEnd = 0  // Margen derecho reducido
                    holder.bodyAccept.layoutParams = params
                }else{
                    holder.body.background = holder.itemView.context.getDrawable(R.drawable.element_beig)
                    val params = holder.body.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.END
                    params.marginStart = 385 // Margen izquierdo para mensajes enviados
                    params.marginEnd = 0  // Margen derecho reducido
                    holder.body.layoutParams = params
                }
            }
        }
    }
    override fun getItemCount(): Int = messageList.size
}