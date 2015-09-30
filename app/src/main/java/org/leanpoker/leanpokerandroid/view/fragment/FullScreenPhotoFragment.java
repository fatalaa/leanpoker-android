package org.leanpoker.leanpokerandroid.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.presenter.FullScreenPhotoPresenter;
import org.leanpoker.leanpokerandroid.view.FullScreenPhotoView;
import org.leanpoker.leanpokerandroid.view.image.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by tmolnar on 29/09/15.
 */
public class FullScreenPhotoFragment extends BaseFragment implements FullScreenPhotoView,
        PhotoViewAttacher.OnPhotoTapListener {

    private FullScreenPhotoPresenter    mFullScreenPhotoPresenter;
    private PhotoModel                  mPhotoModel;

    @Bind(R.id.fullscreen_photo)
    PhotoView mPhotoView;

    @Bind(R.id.fullscreen_photo_overlay)
    LinearLayout mOverlay;

    @Bind(R.id.fullscreen_photo_uploaded_at)
    TextView mUploadedAt;

    @Bind(R.id.fullscreen_photo_uploaded_by)
    TextView mUploadedBy;

    public FullScreenPhotoFragment() {}

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(
                R.layout.fragment_photo_fullscreen,
                container,
                false);
        ButterKnife.bind(this, fragmentView);
        setupUI();
        return fragmentView;
    }

    private void setupUI() {
        final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(mPhotoView);
        photoViewAttacher.setOnPhotoTapListener(this);
        mOverlay.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        loadPhoto();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFullScreenPhotoPresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFullScreenPhotoPresenter.destroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mFullScreenPhotoPresenter.pause();
    }

    private void initialize() {
        mFullScreenPhotoPresenter = new FullScreenPhotoPresenter(mPhotoModel);
        mFullScreenPhotoPresenter.setFullScreenPhotoView(this);
    }

    private void loadPhoto() {
        mFullScreenPhotoPresenter.loadPhoto();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(final String message) {

    }

    public static FullScreenPhotoFragment newInstance(final PhotoModel photoModel){
        FullScreenPhotoFragment fullScreenPhotoFragment = new FullScreenPhotoFragment();
        fullScreenPhotoFragment.setPhotoModel(photoModel);
        return fullScreenPhotoFragment;
    }

    public void setPhotoModel(final PhotoModel photoModel) {
        mPhotoModel = photoModel;
    }

    @Override
    public void renderPhoto(final PhotoModel photoModel) {
        mUploadedAt.setText(photoModel.getUploaded());
        mUploadedBy.setText(photoModel.getOwner());
        ImageLoader.getInstance().aspectedLoad(photoModel.getUrl(), mPhotoView);
    }

    @Override
    public void showOverlay(final boolean showOverlay) {
        if (showOverlay) {
            mOverlay.setVisibility(View.VISIBLE);
            mOverlay.animate()
                    .alpha(1f)
                    .setDuration(1000)
                    .setListener(null);
        } else {
            mOverlay.animate()
                    .alpha(0f)
                    .setDuration(1000)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(final Animator animation) {
                            mOverlay.setVisibility(View.GONE);
                        }
                    });
        }
    }

    @Override
    public void onPhotoTap(final View view, final float x, final float y) {
        mFullScreenPhotoPresenter.toggleOverlayVisibility();
    }
}
