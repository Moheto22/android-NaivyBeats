package com.example.naivybeats.activities.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.naivybeats.R
import com.example.naivybeats.models.post.model.PostLike
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PostAdapter(
    private val publications: List<PostLike>,
    private val coroutineScope: CoroutineScope,
    private val requireContext: Context,
    private val user_id: Int?
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
            }
        }
        holder.follow.setOnClickListener {
            if (publication.follow == 0){
                publication.follow = 1
                coroutineScope.launch {
                    Tools.sendFollow(user_id!!, publication.userId)
                    holder.follow.text = getString(requireContext,R.string.following_eng)
                }
            }
        }

    }

    override fun getItemCount(): Int = publications.size
}