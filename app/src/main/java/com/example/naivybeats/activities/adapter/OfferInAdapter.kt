package com.example.naivybeats.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import kotlinx.coroutines.launch
import com.example.naivybeats.models.offer.models.OfferIn
import kotlinx.coroutines.CoroutineScope

class OfferInAdapter(private val offerList: List<OfferIn>,private val coroutineScope: CoroutineScope) :
    RecyclerView.Adapter<OfferInAdapter.OfferViewHolder>() {

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName: TextView = itemView.findViewById(R.id.name_restaurant)
        val publishDate: TextView = itemView.findViewById(R.id.date)
        val description: TextView = itemView.findViewById(R.id.description)
        val styles: TextView = itemView.findViewById(R.id.prefered_styles)
        val eventDate: TextView = itemView.findViewById(R.id.date_offer)
        var salaryText: TextView = itemView.findViewById(R.id.salary)
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
            }
        }

    private fun generateStringStyles(listStyles: List<String>, stylesIds: List<Int>): String {
        var string = "( "
        for (i in 0..stylesIds.size) {
            if (i == stylesIds.size-1){
                string = string + listStyles[i] + " - "
            }else{
                string = string + listStyles[i]
            }
            string = " )"
        }
        return string
    }

    override fun getItemCount(): Int = offerList.size
}