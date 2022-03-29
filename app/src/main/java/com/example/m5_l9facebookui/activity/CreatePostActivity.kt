package com.example.m5_l9facebookui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.example.m5_l9facebookui.R
import com.example.m5_l9facebookui.model.CreatePost
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL


class CreatePostActivity : AppCompatActivity() {

    private lateinit var etLink: EditText
    private lateinit var ivImage:ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescripyion:TextView
    private lateinit var llCreate: LinearLayout
    private lateinit var btnLoad:MaterialButton

    lateinit var send: ((createPost:CreatePost) -> Unit)


    private lateinit var btnRemove:ImageView

    private lateinit var url:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post2)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        initViews()
    }


    private fun initViews() {
        etLink = findViewById(R.id.et_link)
        ivImage = findViewById(R.id.iv_image)
        tvTitle = findViewById(R.id.tv_title)
        tvDescripyion = findViewById(R.id.tv_description)
        llCreate = findViewById(R.id.ll_create)
        btnRemove = findViewById(R.id.click_remove)

        btnLoad = findViewById(R.id.btn_post)


        etLink.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                url = etLink.text.toString()
                Model().execute()
                llCreate.visibility = View.VISIBLE

            }

        })

        btnRemove.setOnClickListener {
            llCreate.visibility = View.GONE
        }


    }



    inner class Model(): AsyncTask<Void, Void, Void>() {

        private var bitmap: Bitmap? = null
        private lateinit var title:String
        private lateinit var description: String

        private lateinit var imgSrc:String



        override fun onPreExecute() {
            super.onPreExecute()

        }
        override fun doInBackground(vararg p0: Void?): Void? {

            try {
                //connect url
                val document = Jsoup.connect(url).get()
                //description
                description = document.select("meta[property=og:description]").attr("content")
                //title
                title = document.select("meta[property=og:title]").attr("content")

                //image
                val img = document.select("meta[property=og:image]")
                imgSrc = img.attr("content")
                val input: InputStream = URL(imgSrc).openStream()
                bitmap = BitmapFactory.decodeStream(input)


                Log.d("docum", document.toString())
                Log.d("image", imgSrc)
                Log.d("width", img.attr("width"))

            }catch (e: IOException) {

            }
            return null
        }

        override fun onPostExecute(result: Void?) {

            Picasso.get().load(imgSrc).into(ivImage)
            //ivImage.setImageBitmap(bitmap)

            tvTitle.text = title
            tvDescripyion.text = description

            val createPost = CreatePost(imgSrc, title, description)

            btnLoad.setOnClickListener {
                newPost(createPost)
            }


        }

        fun newPost(createPost: CreatePost) {
            val intent = Intent()
            intent.putExtra("createPost", createPost)
            setResult(RESULT_OK, intent)
            finish()
        }

    }






    }

