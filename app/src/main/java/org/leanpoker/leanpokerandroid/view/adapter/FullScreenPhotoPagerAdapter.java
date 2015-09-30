package org.leanpoker.leanpokerandroid.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.view.fragment.FullScreenPhotoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmolnar on 29/09/15.
 */
public class FullScreenPhotoPagerAdapter extends FragmentPagerAdapter {

    private static final String     TAG = FullScreenPhotoPagerAdapter.class.getSimpleName();
    private Context                 mContext;
    private int                     mLastIndex;
    private final List<FullScreenPhotoFragment> mPhotoFragments;

    public FullScreenPhotoPagerAdapter(final FragmentManager fragmentManager, final Context context,
                                       final List<PhotoModel> photoModels, final int lastIndex) {
        super(fragmentManager);
        mContext = context;
        mLastIndex = lastIndex;
        mPhotoFragments = createFragments(photoModels);
    }

    private List<FullScreenPhotoFragment> createFragments(final List<PhotoModel> photoModels) {
        List<FullScreenPhotoFragment> fragments = new ArrayList<>(photoModels.size());
        for (PhotoModel photoModel : photoModels) {
            fragments.add(FullScreenPhotoFragment.newInstance(photoModel));
        }
        return fragments;
    }

    @Override
    public int getCount() {
        return mPhotoFragments.size();
    }

    @Override
    public Fragment getItem(final int position) {
        return mPhotoFragments.get(position);
    }
}
