package com.example.m5_l9facebookui.adapter

import android.content.Context
import android.content.IntentFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m5_l9facebookui.R
import com.example.m5_l9facebookui.activity.MainActivity
import com.example.m5_l9facebookui.model.Feed
import com.example.m5_l9facebookui.model.Story
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import java.lang.Exception

class FeedAdapter(val context: MainActivity, val items:ArrayList<Feed>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_CREATED_POST = 3



    override fun getItemViewType(position: Int): Int {
        val feed = items[position]

        if (feed.isHeader) return TYPE_ITEM_HEAD
        if(feed.stories.size > 0 ) return TYPE_ITEM_STORY
        if (feed.createPost != null) return TYPE_ITEM_CREATED_POST
        return TYPE_ITEM_POST
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_HEAD){
            return HeadViewHolder(context, LayoutInflater.from(parent.context).inflate(R.layout.item_feed_head, parent, false))
        }
        if (viewType == TYPE_ITEM_CREATED_POST) {
            return CreatedPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed_new_post, parent, false))
        }


        if (viewType == TYPE_ITEM_STORY){
            return StoryViewHolder(context, LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false))
        }
        return PostViewHolder(context,LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is HeadViewHolder) {
            holder.apply {
                clickCreatePost.setOnClickListener {
                    context.openCreatePost()

                }
            }
        }

        if (holder is StoryViewHolder) {

            holder.apply {
                if (adapter == null) {
                    adapter = StoryAdapter(context, feed.stories)
                    rv.adapter = adapter
                    rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }


                //refreshAdapter(feed.stories, rv)
            }
        }

        if (holder is PostViewHolder) {
            holder.apply {
                ivProfile.setImageResource(feed.post!!.profile)
                ivPhoto.setImageResource(feed.post!!.photo)
                tvFullname.text = feed.post!!.fullname
            }
        }

        if (holder is CreatedPostViewHolder) {
            holder.apply {
                Picasso.get().load(feed.createPost!!.image).into(ivPhoto)
                tvTitle.text = feed.createPost!!.title
                tvDescription.text = feed.createPost!!.description
            }
        }
    }



    private fun refreshAdapter(stories: ArrayList<Story>, rv:RecyclerView) {
        val adapter = StoryAdapter(context, stories)
        rv.adapter = adapter
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class HeadViewHolder(context: Context, view:View) :RecyclerView.ViewHolder(view) {
        val clickCreatePost = view.findViewById<TextView>(R.id.click_create_post)

    }

    class StoryViewHolder(context: Context, view:View) :RecyclerView.ViewHolder(view) {
        var adapter: StoryAdapter? = null
        val rv = view.findViewById<RecyclerView>(R.id.rv_story)
    }

    class PostViewHolder(context: Context, view:View) :RecyclerView.ViewHolder(view) {
        val ivProfile = view.findViewById<ShapeableImageView>(R.id.iv_profile)
        val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)
        val tvFullname = view.findViewById<TextView>(R.id.tv_fullname)
    }

    class CreatedPostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)

    }




}