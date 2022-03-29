package com.example.m5_l9facebookui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m5_l9facebookui.R
import com.example.m5_l9facebookui.adapter.FeedAdapter
import com.example.m5_l9facebookui.model.CreatePost
import com.example.m5_l9facebookui.model.Feed
import com.example.m5_l9facebookui.model.Post
import com.example.m5_l9facebookui.model.Story

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:FeedAdapter

    private var feeds:ArrayList<Feed> = ArrayList()

    private lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        initViews()

    }


    private fun initViews() {
        rvMain = findViewById(R.id.rv_main)
        rvMain.layoutManager = GridLayoutManager(this, 1)
        refreshAdapter(getAllFeeds())

    }


    fun openCreatePost() {
        val intent = Intent(this, CreatePostActivity::class.java)
        createLauncher.launch(intent)
    }

    var createLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data:Intent? = result.data
            var createPost = data!!.getParcelableExtra<CreatePost>("createPost")

            feeds.add(2, Feed(CreatePost(createPost!!.image, createPost!!.title, createPost!!.description)))

            adapter.notifyItemChanged(2)
        }
    }

    private fun refreshAdapter(feeds:ArrayList<Feed>) {
        adapter = FeedAdapter(this, feeds)
        rvMain.adapter = adapter
    }

    private fun getAllFeeds() : ArrayList<Feed> {
        val stories = ArrayList<Story>()

        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))
        stories.add(Story(R.drawable.img_profile, "Shahriyor Abdullayev"))


        feeds.add(Feed())

        feeds.add(Feed(stories))


        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.img_profile, "Shahriyor", R.drawable.im_post)))


        return feeds
    }





}