package com.example.naivybeats.activities.adapter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.offer.models.OfferIn
import com.example.naivybeats.models.post.model.PostLike
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

class PostAdapter(
    private val publications: List<PostLike>,
    private val coroutineScope: CoroutineScope,
    private val requireContext: Context,
    private val user_id: Int?,
    private val panelSendOffer: LinearLayout?,
    private val salarys: EditText?,
    private val dateTextView: TextView?,
    private val buttonSend: Button?
) : RecyclerView.Adapter<PostAdapter.PublicationViewHolder>() {

    inner class PublicationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameUser: TextView = view.findViewById(R.id.name_user)
        val date: TextView = view.findViewById(R.id.date)
        val title : TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val image: ImageView = view.findViewById(R.id.image)
        val avatar: ImageView = view.findViewById(R.id.avatar)
        val likeCount: TextView = view.findViewById(R.id.count_like)
        val likeButton: ImageView = view.findViewById(R.id.like)
        val follow :Button = view.findViewById(R.id.follow)
        val buttonSendOffer : Button = view.findViewById(R.id.send_personal_offer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_publication, parent, false)
        return PublicationViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PublicationViewHolder, position: Int) {
        val publication = publications[position]
        holder.date.text = publication.postDate
        holder.title.text = publication.title
        holder.description.text = publication.description
        holder.likeCount.text = "0"
        if (publication.like == 1){
            holder.likeButton.setImageResource(R.drawable.heart_on)
        }
        if (publication.follow == 1){
            holder.follow.text = getString(requireContext,R.string.following_eng)
        }
        coroutineScope.launch {
            val user = Tools.userOrRestaurant(publication.userId)
            holder.nameUser.text = user?.name
            val file = user?.let { Tools.getImage(Tools.generatePathForImages(publication.multimedia)) }
            file?.let {
                val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                holder.image.setImageBitmap(bitmap)
            }
            val file2 = user?.let { Tools.getImage(Tools.generatePathForImages(user.photo)) }
            file2?.let {
                val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                holder.avatar.setImageBitmap(bitmap)
            }
        }
        holder.likeButton.setOnClickListener {
            if (publication.like == 0) {
                publication.like = 1
                coroutineScope.launch {
                    Tools.sendLike(user_id!!, publication.postId)
                    holder.likeButton.setImageResource(R.drawable.heart_on)
                    // actualizar numero de likes
                }
            }else{
                publication.like = 0
                coroutineScope.launch {
                    Tools.unlike(user_id!!, publication.postId)
                    holder.likeButton.setImageResource(R.drawable.heart_off)
                    // actualizar numero de likes
            }
        }
            holder.buttonSendOffer.setOnClickListener {
                panelSendOffer?.visibility = View.VISIBLE
            }
        holder.follow.setOnClickListener {
            if (publication.follow == 0){
                publication.follow = 1
                coroutineScope.launch {
                    Tools.sendFollow(user_id!!, publication.userId)
                    holder.follow.text = getString(requireContext,R.string.following_eng)
                }
            }else{
                publication.follow = 0
                coroutineScope.launch {
                    Tools.unfollow(user_id!!, publication.userId)
                    holder.follow.text = getString(requireContext,R.string.follow_eng)
                }
            }
        }


            dateTextView!!.setOnClickListener(){
                val currentDate = Calendar.getInstance()
                DatePickerDialog(
                    requireContext,
                    { _, year, month, dayOfMonth ->
                        // Cuando se selecciona la fecha, mostramos el TimePicker
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(Calendar.YEAR, year)
                        selectedDate.set(Calendar.MONTH, month)
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        TimePickerDialog(
                            requireContext,
                            { _, hourOfDay, minute ->
                                selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                selectedDate.set(Calendar.MINUTE, minute)
                                // Formateamos la fecha y la ponemos en el botón
                                val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                                dateTextView.text = format.format(selectedDate.time)

                            },
                            currentDate.get(Calendar.HOUR_OF_DAY),
                            currentDate.get(Calendar.MINUTE),
                            true
                        ).show()

                    },
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                ).show()

            }
            buttonSend?.setOnClickListener {
                val salary = salarys?.text.toString()
                val dateText = dateTextView?.text.toString()
                val message = salary + "|" + dateTextView
                val chat = Chat(null,null,user_id!!,publication.userId!!)
                var idOffer : Int
                var finalChat : Chat
                coroutineScope.launch {
                    idOffer = Tools.newOffer(OfferIn(null,null,salary as Int,dateText,null,user_id!!,null,null,null))!!
                    if (publication.chat == 1){
                        finalChat = Tools.getChatByMusicianAndRestaurantId(chat)
                        Tools.newMessage(Message(null,null,finalChat.chatId,user_id,null,message,idOffer,null))
                    }else{
                        Tools.newChat(chat)
                        finalChat = Tools.getChatByMusicianAndRestaurantId(chat)
                        Tools.newMessage(Message(null,null,finalChat.chatId,user_id,null,message,idOffer,null))
                    }
                    salarys?.text?.clear()
                    dateTextView?.text = "00/00/0000 00:00"
                    panelSendOffer?.visibility = View.GONE
                }

            }
        }

    }

    override fun getItemCount(): Int = publications.size
}