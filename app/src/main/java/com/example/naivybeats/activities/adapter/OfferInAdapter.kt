package com.example.naivybeats.adapters

import Tools
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.offer.models.OfferDto
import kotlinx.coroutines.launch
import com.example.naivybeats.models.offer.models.OfferIn
import com.example.naivybeats.models.offer.models.PostOffer
import kotlinx.coroutines.CoroutineScope

class OfferInAdapter(
    private val offerList: List<OfferDto>,
    private val coroutineScope: CoroutineScope,
    private val requireContext: Context,
    private val user_id: Int
) :
    RecyclerView.Adapter<OfferInAdapter.OfferViewHolder>() {

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName: TextView = itemView.findViewById(R.id.name_restaurant)
        val publishDate: TextView = itemView.findViewById(R.id.date)
        val description: TextView = itemView.findViewById(R.id.description)
        val styles: TextView = itemView.findViewById(R.id.prefered_styles)
        val eventDate: TextView = itemView.findViewById(R.id.date_offer)
        var salaryText: TextView = itemView.findViewById(R.id.salary)
        val buttonReadMore: Button = itemView.findViewById(R.id.read_more)
        val buttonPostulate : Button = itemView.findViewById(R.id.postulate)
        val imageRest : ImageView = itemView.findViewById(R.id.image_restaurant)
        val information : LinearLayout = itemView.findViewById(R.id.more_info)
        val sendFirstMessageMusician : LinearLayout = itemView.findViewById(R.id.send_first_message)
        val buttonSendFirstMessageMusician : Button = itemView.findViewById(R.id.button_send_first)
        val editTextSendFirstMessageMusician : EditText = itemView.findViewById(R.id.text_first_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_ofert, parent, false)
        return OfferViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
            coroutineScope.launch {
                val offer = offerList[position]
                val listStyles = listOf("Hip-hop","Pop","Tecno","Classic","Flamenco","Reggueton","Rock","Blues","Jazz","Trap")
                val styles = generateStringStyles(listStyles,offer.styles_ids)
                holder.publishDate.text = offer.publish_date
                holder.description.text = offer.description
                holder.styles.text = styles
                holder.eventDate.text = offer.event_date
                holder.salaryText.text = offer.salary.toString() + " €"
                val user = Tools.userOrRestaurant(offer.restaurant_id)
                holder.restaurantName.text = user?.name
                val file = user?.let { Tools.getImage(Tools.generatePathForImages(user.photo)) }
                file?.let {
                    val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                    holder.imageRest.setImageBitmap(bitmap)
                }
                if (offer.postulated == 1){
                    holder.buttonPostulate.text = getString(requireContext,R.string.all_ready_postulated_eng)
                }
                holder.buttonReadMore.setOnClickListener(){
                    val text = holder.buttonReadMore.text.toString()
                    if (text == getString(requireContext,R.string.read_more_eng)) {
                        holder.information.visibility = View.VISIBLE
                        holder.buttonReadMore.text = getString(requireContext,R.string.read_less_eng)
                    }else{
                        holder.information.visibility = View.GONE
                        holder.buttonReadMore.text = getString(requireContext,R.string.read_more_eng)
                    }
                }
                holder.buttonPostulate.setOnClickListener(){
                    if (holder.buttonPostulate.text == getString(requireContext,R.string.postulate_eng)){
                        holder.sendFirstMessageMusician.visibility = View.VISIBLE
                    }
                }
                holder.buttonSendFirstMessageMusician.setOnClickListener(){
                    var id_chat : Chat
                    val text = holder.editTextSendFirstMessageMusician.text.toString()
                    if (!text.isEmpty()) {
                        val chat = Chat(null, null, offer.restaurant_id, user_id)
                        coroutineScope.launch {
                            id_chat = Tools.getChatByMusicianAndRestaurantId(chat)
                            if (id_chat == null){
                                Tools.newChat(chat)
                                Thread.sleep(1000)
                                id_chat = Tools.getChatByMusicianAndRestaurantId(chat)
                            }
                            val message = Message(0,null,id_chat.chatId,user_id,null,text,null,null)
                            Tools.newMessage(message)
                            holder.sendFirstMessageMusician.visibility = View.INVISIBLE
                            holder.editTextSendFirstMessageMusician.text.clear()
                            Tools.newPostOffer(PostOffer(user_id,offer.offer_in_id))
                            holder.buttonPostulate.text = getString(requireContext,R.string.all_ready_postulated_eng)
                        }
                    }else{
                        Toast.makeText(requireContext, getString(requireContext,R.string.doesnt_exist_text_eng),Toast.LENGTH_LONG)
                        holder.sendFirstMessageMusician.visibility = View.INVISIBLE
                    }
                }
            }
        }

    private fun generateStringStyles(listStyles: List<String>, stylesIds: List<Int>): String {
        var string = "( "
        for (i in 0..< stylesIds.size){
            if (i != stylesIds.size-1){
                string = string + listStyles[i] + " - "
            }else{
                string = string + listStyles[i]
            }
        }
        string = string + " )"
        if (stylesIds.isEmpty()){
            string = getString(requireContext,R.string.no_style_eng)
        }
        return string
    }



    override fun getItemCount(): Int = offerList.size
}