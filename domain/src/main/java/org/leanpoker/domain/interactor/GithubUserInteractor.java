package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by tmolnar on 24/09/15.
 */
public class GithubUserInteractor extends BaseInteractor {
    public GithubUserInteractor() {
        super();
    }

    @Override
    protected Observable buildInteractorObservable() {
        return NetworkManager.getInstance().getUser(false);
    }
}
