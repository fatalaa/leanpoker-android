package org.leanpoker.leanpokerandroid.presenter;

import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.view.FullScreenPhotoView;

/**
 * Created by tmolnar on 29/09/15.
 */
public class FullScreenPhotoPresenter implements Presenter {

    private FullScreenPhotoView         mFullScreenPhotoView;
    private final PhotoModel            mPhotoModel;
    private boolean                     mShowOverlay;

    public FullScreenPhotoPresenter(final PhotoModel photoModel) {
        mPhotoModel = photoModel;
        mShowOverlay = false;
    }

    public void loadPhoto() {
        mFullScreenPhotoView.renderPhoto(mPhotoModel);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void setFullScreenPhotoView(final FullScreenPhotoView mFullScreenPhotoView) {
        this.mFullScreenPhotoView = mFullScreenPhotoView;
    }

    public void toggleOverlayVisibility() {
        mShowOverlay = !mShowOverlay;
        mFullScreenPhotoView.showOverlay(mShowOverlay);
    }
}
