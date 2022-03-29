package com.example.m5_l9facebookui.model

class Feed {

    var isHeader: Boolean = false
    var post: Post? = null
    var createPost: CreatePost? = null
    var stories: ArrayList<Story> = ArrayList()

    constructor(){
        this.isHeader = true
    }

    constructor(post: Post) {
        this.post = post
        this.isHeader = false
    }

    constructor(createPost: CreatePost) {
        this.createPost = createPost
        this.isHeader = false
    }

    constructor(stories: ArrayList<Story>) {
        this.stories = stories
        this.isHeader = false
    }
}