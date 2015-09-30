package org.leanpoker.leanpokerandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import org.leanpoker.leanpokerandroid.P;
import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.view.adapter.FullScreenPhotoPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tmolnar on 27/09/15.
 */
public class FullScreenPhotoActivity extends BaseActivity {

    @Bind(R.id.fullscreen_photo_viewpager)
    ViewPager                           mViewPager;
    private FullScreenPhotoPagerAdapter mFullScreenPhotoPagerAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fullscreen);
        ButterKnife.bind(this);
        List<PhotoModel> photoModels =
                getIntent().getParcelableArrayListExtra(P.FullScreenPhotoActivity.PHOTOS_LIST);
        int clickedIndex = getIntent().getIntExtra(P.FullScreenPhotoActivity.PHOTO_INDEX_KEY, 0);
        setupUI(photoModels, clickedIndex);
    }

    private void setupUI(final List<PhotoModel> photoModels, final int clickedIndex) {
        mFullScreenPhotoPagerAdapter = new FullScreenPhotoPagerAdapter(
                getSupportFragmentManager(),
                this,
                photoModels,
                clickedIndex
        );
        mViewPager.setAdapter(mFullScreenPhotoPagerAdapter);
    }

    public static Intent createIntent(final Context context, final ArrayList<PhotoModel> photoModels,
                                      final int clickedPhotoIndex) {
        final Intent intent = new Intent(context, FullScreenPhotoActivity.class);
        intent.putParcelableArrayListExtra(P.FullScreenPhotoActivity.PHOTOS_LIST, photoModels);
        intent.putExtra(P.FullScreenPhotoActivity.PHOTO_INDEX_KEY, clickedPhotoIndex);
        return intent;
    }
}
