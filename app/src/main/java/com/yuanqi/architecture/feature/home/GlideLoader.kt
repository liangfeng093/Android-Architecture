package com.yuanqi.architecture.feature.home

import android.content.Context
import android.widget.ImageView
import cn.levey.bannerlib.impl.RxBannerLoaderInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by mzf on 2018/7/26.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class GlideLoader : RxBannerLoaderInterface<ImageView> {
    //class GlideLoader : RxBannerLoaderInterface<ImageView> {
    override fun create(context: Context?): ImageView {
        return ImageView(context)
    }

    override fun show(context: Context?, path: Any?, iv: ImageView?) {
        Glide.with(context)
                ?.load(path)
                ?.apply(RequestOptions.centerCropTransform())
                ?.into(iv)
    }
}