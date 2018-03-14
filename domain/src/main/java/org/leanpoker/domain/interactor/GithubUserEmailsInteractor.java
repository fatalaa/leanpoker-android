package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by tmolnar on 24/09/15.
 */
public class GithubUserEmailsInteractor extends BaseInteractor {
    public GithubUserEmailsInteractor() {
        super();
    }

    @Override
    protected Observable buildInteractorObservable() {
        return NetworkManager.getInstance().getUser(true);
    }
}
