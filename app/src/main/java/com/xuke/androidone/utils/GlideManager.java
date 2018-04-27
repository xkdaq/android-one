package com.xuke.androidone.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * author: LJL.
 * date: 2017/3/22 11:17
 */

public class GlideManager {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static class ImageLoaderHolder {
        private static final GlideManager INSTANCE = new GlideManager();

    }

    private GlideManager() {
    }

    public static final GlideManager getInstance() {
        return ImageLoaderHolder.INSTANCE;
    }


    //直接加载网络图片
    public void loadImage(Context context, ImageView imageView, String url) {
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(new ColorDrawable(0xffE7E7E7))
                .crossFade()
                .into(imageView);
    }

    //直接加载网络图片
    public void loadImage(Context context, ImageView imageView, String url, boolean isCrop) {
        if (isCrop) {
            loadImage(context, imageView, url);
        } else {
            Glide
                    .with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(new ColorDrawable(0xffE7E7E7))
                    .crossFade()
                    .into(imageView);
        }
    }

    public void loadImage(Context context, ImageView imageView, String url, int placeHolder) {
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(placeHolder)
                .crossFade()
                .dontAnimate()
                .into(imageView);
    }

    //加载SD卡图片
    public void loadImage(Context context, ImageView imageView, File file) {
        Glide
                .with(context)
                .load(file)
                .centerCrop()
                .dontAnimate()
                .into(imageView);

    }

    //加载SD卡图片并设置大小
    public void loadImage(Context context, ImageView imageView, File file, int width, int height) {
        Glide
                .with(context)
                .load(file)
                .placeholder(new ColorDrawable(0xffE7E7E7))
                .override(width, height)
                .centerCrop()
                .into(imageView);

    }

    //加载网络图片并设置大小
    public void loadImage(Context context, ImageView imageView, String url, int width, int height) {
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(new ColorDrawable(0xffE7E7E7))
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    //加载drawable图片
    public void loadImage(Context context, ImageView imageView, int resId) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .crossFade()
                .into(imageView);
    }


    //将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

}

