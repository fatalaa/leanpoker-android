package org.leanpoker.leanpokerandroid.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by tmolnar on 29/09/15.
 */
public class ZoomableImageView extends ImageView {

    private String TAG = ZoomableImageView.class.getSimpleName();

    private PhotoViewAttacher mAttacher;

    public ZoomableImageView(Context context) {
        super(context, null);
    }

    public ZoomableImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ZoomableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if(mAttacher != null) {
            mAttacher.update();
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if(mAttacher != null) {
            mAttacher.update();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttacher.cleanup();
        mAttacher = null;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttacher = new PhotoViewAttacher(this);
        mAttacher.setZoomable(true);
    }
}
