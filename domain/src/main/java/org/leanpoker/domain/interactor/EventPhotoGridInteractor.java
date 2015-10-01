package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import rx.Observable;

/**
 * Created by tmolnar on 21/09/15.
 */
public class EventPhotoGridInteractor extends BaseInteractor {

    private final String mEventId;

    public EventPhotoGridInteractor(final String mEventId) {
        super();
        this.mEventId = mEventId;
    }

    @Override
    protected Observable buildInteractorObservable() {
        return NetworkManager.getInstance().photos(mEventId);
    }
}
