package com.example.cocofa

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class TipsActivity : AppCompatActivity() {
    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        dbHelper = MyDatabaseHelper(this)

        val postRecyclerView = findViewById<RecyclerView>(R.id.tip_recycler_view)
        val postAdapter = PostAdapter(dbHelper.getAllPosts())
        postRecyclerView.adapter = postAdapter

    }
}

class PostAdapter(private val posts: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_card_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount() = posts.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            // Bind the post data to the view holder views
            val descriptionTextView = itemView.findViewById<TextView>(R.id.post_dis_textview)
            descriptionTextView.text = post.description

            val imageView = itemView.findViewById<ImageView>(R.id.post_img)
            val bitmap = post.image?.let { BitmapFactory.decodeByteArray(post.image, 0, it.size) }
            imageView.setImageBitmap(bitmap)
        }
    }
}
