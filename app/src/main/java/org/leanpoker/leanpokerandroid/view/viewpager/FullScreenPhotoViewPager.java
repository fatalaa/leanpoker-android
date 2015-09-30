package org.leanpoker.leanpokerandroid.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by tmolnar on 29/09/15.
 */
public class FullScreenPhotoViewPager extends ViewPager {

    private static final String TAG = FullScreenPhotoViewPager.class.getSimpleName();

    private boolean mLocked;

    public FullScreenPhotoViewPager(final Context context) {
        super(context);
        mLocked = false;
    }

    public FullScreenPhotoViewPager(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mLocked = false;
    }

    public boolean isLocked() {
        return mLocked;
    }

    public void setLocked(final boolean mLocked) {
        this.mLocked = mLocked;
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent ev) {
        if (!mLocked) {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, e.getMessage());
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent ev) {
        return !mLocked && super.onTouchEvent(ev);
    }

    public void toggleLock() {
        mLocked = !mLocked;
    }
}
