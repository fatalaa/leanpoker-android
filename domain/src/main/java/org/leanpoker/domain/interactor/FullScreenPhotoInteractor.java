package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import rx.Observable;

/**
 * Created by tmolnar on 30/09/15.
 */
public class FullScreenPhotoInteractor extends BaseInteractor {
    private final String mEventId;

    public FullScreenPhotoInteractor(final String mEventId) {
        this.mEventId = mEventId;
    }

    @Override
    protected Observable buildInteractorObservable() {
        return NetworkManager.getInstance().photos(mEventId);
    }
}
