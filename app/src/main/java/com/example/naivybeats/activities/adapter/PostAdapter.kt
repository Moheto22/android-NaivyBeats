package com.example.naivybeats.activities.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.models.post.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PostAdapter(
    private val publications: List<Post>,
    private val coroutineScope: CoroutineScope
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
        // Puedes agregar más views si los necesitas
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
        holder.image.setImageResource(R.color.beig_trans_plus)
        holder.avatar.setImageResource(R.color.white)
        holder.likeCount.text = "0"
        coroutineScope.launch {
            val user = Tools.userOrRestaurant(publication.userId)
            holder.nameUser.text = user?.name

        }
    }

    override fun getItemCount(): Int = publications.size
}