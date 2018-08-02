package com.yuanqi.architecture.feature.talk

import android.content.Context
import android.widget.ImageView
import cn.jiguang.imui.commons.ImageLoader
import com.bumptech.glide.Glide
import com.yuanqi.architecture.R

/**
 * Created by mzf on 2018/8/2.
 * Email:liangfeng093@gmail.com
 * Desc:图片加载器
 */
class ImgLoader : ImageLoader {

    var context: Context? = null

    constructor(context: Context?) {
        this.context = context
    }


    override fun loadAvatarImage(avatarImageView: ImageView?, string: String?) {

        Glide.with(context)
                ?.load(R.mipmap.ic_crash)
                ?.into(avatarImageView)
    }

    override fun loadImage(imageView: ImageView?, string: String?) {
        Glide.with(context)
                ?.load(string)
                ?.into(imageView)
    }

    override fun loadVideo(imageCover: ImageView?, uri: String?) {
    }
}