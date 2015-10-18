package org.leanpoker.leanpokerandroid.view.image;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.leanpoker.leanpokerandroid.MainApplication;

/**
 * Created by tmolnar on 21/09/15.
 */
public class ImageLoader {

    private Context                 mContext;
    private Picasso                 mPicasso;
    private static ImageLoader      mInstance;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    public void load(String url, ImageView imageView) {
        if (mPicasso == null) {
            throw new IllegalStateException("Tried to access un-initialized Picasso instance");
        }
        mPicasso.load(url).fit().centerCrop().into(imageView);
    }

    public void load(String url, ImageView imageView, Callback callback) {
        if (mPicasso == null) {
            throw new IllegalStateException("Tried to access un-initialized Picasso instance");
        }
        mPicasso.load(url).fit().into(imageView, callback);
    }

    public void aspectedLoad(String url, ImageView imageView) {
        if (mPicasso == null) {
            throw new IllegalStateException("Tried to access un-initialized Picasso instance");
        }
        mPicasso.load(url).fit().centerInside().into(imageView);
    }

    public void init(final Context context) {
        mContext = context;
        mPicasso = Picasso.with(mContext);
    }
}
