package org.leanpoker.leanpokerandroid.view.image;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.leanpoker.leanpokerandroid.R;

/**
 * Created by tmolnar on 21/09/15.
 */
public class ImageLoader {

    private final Picasso       mPicasso;
    public static ImageLoader   mInstance;

    private ImageLoader(Context context) {
        mPicasso = Picasso.with(context);
    }

    public static ImageLoader getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(context);
                }
            }
        }
        return mInstance;
    }

    public void load(String url, ImageView imageView) {
        mPicasso.load(url).resize(300, 300).into(imageView);
    }
}
