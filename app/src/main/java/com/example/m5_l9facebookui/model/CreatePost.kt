package com.example.m5_l9facebookui.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class CreatePost(
    val image: String?,
    val title: String,
    val description:String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreatePost> {
        override fun createFromParcel(parcel: Parcel): CreatePost {
            return CreatePost(parcel)
        }

        override fun newArray(size: Int): Array<CreatePost?> {
            return arrayOfNulls(size)
        }
    }
}


