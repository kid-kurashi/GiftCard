package com.projects.coal.giftcard.feature.domain

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

interface IImageLoader {
    fun loadImage(url: String, imageView: ImageView)
    fun loadImage(url: String, target: Target)
    fun getPicasso(): Picasso
}
