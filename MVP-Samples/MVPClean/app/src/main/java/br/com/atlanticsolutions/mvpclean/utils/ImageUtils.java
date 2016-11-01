package br.com.atlanticsolutions.mvpclean.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public class ImageUtils {

    public static void loadImageFromUrl(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
