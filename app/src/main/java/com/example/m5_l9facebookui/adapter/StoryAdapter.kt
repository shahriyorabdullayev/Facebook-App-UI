package com.example.m5_l9facebookui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m5_l9facebookui.R
import com.example.m5_l9facebookui.model.Story
import com.google.android.material.imageview.ShapeableImageView

class StoryAdapter(val context: Context, val items: ArrayList<Story>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = items[position]

        if (holder is StoryViewHolder) {
            holder.apply {
                ivBackground.setImageResource(story.profile)
                ivProfile.setImageResource(story.profile)
                tvFullname.text = story.fullname
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class StoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivBackground = view.findViewById<ShapeableImageView>(R.id.iv_background)
        val ivProfile = view.findViewById<ShapeableImageView>(R.id.iv_profile)
        val tvFullname = view.findViewById<TextView>(R.id.tv_fullname)
    }
}