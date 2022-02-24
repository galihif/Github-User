package com.giftech.githubuser.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.giftech.githubuser.R

object AppUtils {

    fun ImageView.loadCircleImage(imageSource : String?) {
        Glide.with(context.applicationContext)
            .load(imageSource)
            .centerCrop()
            .circleCrop()
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(this)
    }

}