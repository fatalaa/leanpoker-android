package org.leanpoker.domain.interactor;

import org.leanpoker.api.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by tmolnar on 19/09/15.
 */
public class LoginInteractor extends BaseInteractor {

    private String mAccessCode;
    private String mState;

    public LoginInteractor() {
        super();
    }

    @Override
    protected Observable buildInteractorObservable() {
        if (mAccessCode == null || mState == null) {
            throw new IllegalStateException("Accesscode and state cannot be null");
        }
        return NetworkManager.getInstance().getToken(mAccessCode, mState);
    }

    public void setUpCredentials(String accessCode, String state) {
        mAccessCode = accessCode;
        mState = state;
    }
}
